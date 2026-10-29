const assert = require("node:assert/strict");
const test = require("node:test");
const oauth1 = require("./oauth1.js");

test("percentEncode follows RFC 3986", () => {
    assert.equal(oauth1.percentEncode("a b!*'()|[]~-._"), "a%20b%21%2A%27%28%29%7C%5B%5D~-._");
});

test("signature matches the published OAuth 1.0a example", async () => {
    const {oauthParameters} = await oauth1.sign({
        method: "POST",
        url: "https://api.twitter.com/1.1/statuses/update.json?include_entities=true",
        consumerKey: "xvz1evFS4wEEPTGEFPHBog",
        consumerSecret: "kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw",
        token: "370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb",
        tokenSecret: "LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE",
        parameters: [["status", "Hello Ladies + Gentlemen, a signed OAuth request!"]],
        nonce: "kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg",
        timestamp: 1318622958,
    });
    assert.equal(oauthParameters.oauth_signature, "hCtSmYh+iHYCEqBWrE7C7hYmtUk=");
});

test("field selectors with pipes and brackets are signed on their decoded form", async () => {
    const first = await oauth1.sign({
        method: "GET", url: "https://example.test/services/x/y?fields=id%7Cname%5Bb%5D",
        consumerKey: "key", consumerSecret: "secret", nonce: "n", timestamp: 1,
    });
    const second = await oauth1.sign({
        method: "GET", url: "https://example.test/services/x/y?fields=id|name[b]",
        consumerKey: "key", consumerSecret: "secret", nonce: "n", timestamp: 1,
    });
    assert.equal(first.oauthParameters.oauth_signature, second.oauthParameters.oauth_signature);
});

test("swagger requests are redirected to the chosen installation and signed", async () => {
    const request = {method: "GET", url: "https://apps.usos.pw.edu.pl//services/apiref/method?name=services%2Fusers%2Fuser", headers: {accept: "application/json"}};
    const signed = await oauth1.signSwaggerRequest(request, {consumerKey: "key", consumerSecret: "secret"}, "https://usos.example.edu/");
    assert.equal(signed.url, "https://usos.example.edu/services/apiref/method?name=services%2Fusers%2Fuser");
    assert.match(signed.headers.Authorization, /^OAuth oauth_consumer_key="key", /);
    assert.doesNotMatch(signed.headers.Authorization, /oauth_token=/);
    assert.equal(signed.headers.accept, "application/json");
});

test("swagger requests with an access token are signed with it", async () => {
    const signed = await oauth1.signSwaggerRequest(
        {method: "GET", url: "https://x.test/services/users/user", headers: {}},
        {consumerKey: "key", consumerSecret: "secret", token: "user-token", tokenSecret: "user-secret"},
        "https://usos.example.edu");
    assert.match(signed.headers.Authorization, /oauth_token="user-token"/);
});

test("requests stay untouched without a consumer key and secret", async () => {
    const request = {method: "GET", url: "https://x.test/services/users/user", headers: {}};
    const result = await oauth1.signSwaggerRequest(request, {consumerKey: "", consumerSecret: ""}, "https://usos.example.edu");
    assert.equal(result.url, "https://x.test/services/users/user");
    assert.equal(result.headers.Authorization, undefined);
});
