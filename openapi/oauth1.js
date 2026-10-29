(function (root, factory) {
    const api = factory(globalThis.crypto);
    if (typeof module === "object" && module.exports) {
        module.exports = api;
    } else {
        root.UsosOAuth1 = api;
    }
})(typeof self !== "undefined" ? self : this, function (crypto) {
    const encoder = new TextEncoder();

    function percentEncode(value) {
        return encodeURIComponent(String(value)).replace(/[!'()*]/g, (character) =>
            "%" + character.charCodeAt(0).toString(16).toUpperCase());
    }

    function compare(left, right) {
        if (left === right) {
            return 0;
        }
        return left < right ? -1 : 1;
    }

    function normalizedParameters(pairs) {
        return pairs
            .map(([name, value]) => [percentEncode(name), percentEncode(value)])
            .sort((left, right) => compare(left[0], right[0]) || compare(left[1], right[1]))
            .map(([name, value]) => name + "=" + value)
            .join("&");
    }

    function baseString(method, url, pairs) {
        const parsed = new URL(url);
        const base = parsed.protocol + "//" + parsed.host + parsed.pathname;
        return [method.toUpperCase(), percentEncode(base), percentEncode(normalizedParameters(pairs))].join("&");
    }

    async function hmacSha1(key, text) {
        const imported = await crypto.subtle.importKey("raw", encoder.encode(key), {name: "HMAC", hash: "SHA-1"}, false, ["sign"]);
        const signature = await crypto.subtle.sign("HMAC", imported, encoder.encode(text));
        return btoa(String.fromCharCode(...new Uint8Array(signature)));
    }

    function randomNonce() {
        const bytes = new Uint8Array(16);
        crypto.getRandomValues(bytes);
        return Array.from(bytes, (byte) => byte.toString(16).padStart(2, "0")).join("");
    }

    async function sign({method, url, consumerKey, consumerSecret, token, tokenSecret, parameters = [], oauth = {}, nonce, timestamp}) {
        const oauthParameters = {
            oauth_consumer_key: consumerKey,
            oauth_nonce: nonce || randomNonce(),
            oauth_signature_method: "HMAC-SHA1",
            oauth_timestamp: String(timestamp || Math.floor(Date.now() / 1000)),
            oauth_version: "1.0",
            ...(token ? {oauth_token: token} : {}),
            ...oauth,
        };
        const query = Array.from(new URL(url).searchParams.entries());
        const pairs = [...query, ...parameters, ...Object.entries(oauthParameters)];
        const key = percentEncode(consumerSecret) + "&" + percentEncode(tokenSecret || "");
        oauthParameters.oauth_signature = await hmacSha1(key, baseString(method, url, pairs));
        const header = "OAuth " + Object.entries(oauthParameters)
            .map(([name, value]) => percentEncode(name) + '="' + percentEncode(value) + '"')
            .join(", ");
        return {header, oauthParameters};
    }

    function joinUrl(baseUrl, path) {
        return baseUrl.replace(/\/+$/, "") + "/" + path.replace(/^\/+/, "");
    }

    async function signSwaggerRequest(request, credentials, baseUrl) {
        if (!credentials.consumerKey || !credentials.consumerSecret) {
            return request;
        }
        const original = new URL(request.url);
        request.url = joinUrl(baseUrl, original.pathname) + original.search;
        const {header} = await sign({method: request.method, url: request.url, ...credentials});
        request.headers = {...request.headers, Authorization: header};
        return request;
    }

    async function call(baseUrl, path, query, credentials, oauth) {
        const search = new URLSearchParams(query).toString();
        const url = joinUrl(baseUrl, path) + (search ? "?" + search : "");
        const {header} = await sign({method: "GET", url, ...credentials, oauth});
        const response = await fetch(url, {headers: {Authorization: header}});
        const text = await response.text();
        if (!response.ok) {
            throw new Error(text || "HTTP " + response.status);
        }
        return new URLSearchParams(text);
    }

    async function requestToken(baseUrl, credentials, scopes) {
        const form = await call(baseUrl, "services/oauth/request_token", {scopes},
            {...credentials, token: undefined, tokenSecret: undefined}, {oauth_callback: "oob"});
        return {token: form.get("oauth_token"), tokenSecret: form.get("oauth_token_secret")};
    }

    function authorizeUrl(baseUrl, requestTokenValue) {
        return joinUrl(baseUrl, "services/oauth/authorize") + "?oauth_token=" + percentEncode(requestTokenValue.token);
    }

    async function accessToken(baseUrl, credentials, requestTokenValue, verifier) {
        const form = await call(baseUrl, "services/oauth/access_token", {},
            {...credentials, token: requestTokenValue.token, tokenSecret: requestTokenValue.tokenSecret},
            {oauth_verifier: verifier});
        return {token: form.get("oauth_token"), tokenSecret: form.get("oauth_token_secret")};
    }

    return {percentEncode, baseString, sign, signSwaggerRequest, requestToken, authorizeUrl, accessToken};
});
