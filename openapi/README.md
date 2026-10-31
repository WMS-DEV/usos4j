# OpenAPI specification

`generate_openapi.py` reads the usos4j sources and writes `openapi.json`: every endpoint integrated in usos4j with its parameters, response models and the default `fields` selector. The Swagger page (`index.html`) signs requests with OAuth 1.0a in the browser, so you can try the endpoints with your own consumer key and secret.

```bash
python openapi/generate_openapi.py build/openapi.json
```

## Descriptions from the USOS documentation

Summaries, descriptions, parameter docs and defaults, response docs, authorization (consumer key, access token, scopes), deprecations and the link to the official docs come from the `services/apiref/*` methods of USOS. The generator reads them from the committed `apiref-snapshot.json`, so building the specification needs no network. Methods missing from the snapshot keep the descriptions derived from the usos4j sources.

The `apiref` methods can be called anonymously. Refresh the snapshot after adding endpoints to usos4j or when USOS changes its documentation, and commit the result:

```bash
python openapi/refresh_apiref.py
python openapi/refresh_apiref.py https://usosapps.uw.edu.pl/ services/grades/latest
```

The first argument can be the base URL of another installation (default `https://apps.usos.pw.edu.pl/`), the rest limits the refresh to the given methods. It only does `GET services/apiref/*` calls, pauses between them and stops after 400 calls.

## Client libraries in other languages

The specification works with [OpenAPI Generator](https://openapi-generator.tech). Every endpoint has a unique `operationId`, a tag per USOS module and an `Authorization` header security scheme (`usosConsumer` or `usosUser`).

```bash
java -jar openapi-generator-cli.jar generate -i openapi.json -g python -o usos-python
java -jar openapi-generator-cli.jar generate -i openapi.json -g typescript-fetch -o usos-typescript
java -jar openapi-generator-cli.jar generate -i openapi.json -g go -o usos-go --additional-properties=packageName=usos,isGoSubmodule=false --name-mappings has_email=has_email_flag
```

Python, TypeScript, Java and Go clients were generated and the Python and Go ones compile. Go needs the `--name-mappings` flag, because `UsosUser` has both `email` and `has_email` and the generator creates a `HasEmail()` method for the first one that clashes with the second.

Generated clients do not sign requests. USOS uses OAuth 1.0a (HMAC-SHA1), so give the client an HTTP layer that signs every request with the consumer key and secret, and with the access token for the endpoints marked `usosUser`, for example an OAuth 1.0a transport library of your language. The signing code in `oauth1.js` is a short reference implementation.
