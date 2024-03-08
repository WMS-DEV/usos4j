### Authorization flow (starts with login button clicked):
1) Front-end (main.js): sends request to /auth/request-token
2) Back-end (AuthController): obtains RequestToken from Usos server, generates AuthorizationUrl and sends them back to front-end
3) Front-end (main.js): saves requestToken and requestTokenSecret in session storage(not sensitive info), then redirects user to AuthorizationUrl
4) Usos server: verifiers user, then redirects user back to login page with verifier as a param (when callback supplied, else it shows verifier on the screen).
5) Front-end (main.js): reads verifier, POSTs it along saved RequestToken to /auth/access-token
6) Back-end (AuthController): sends requestToken, requestTokenSecret and verifier to Usos server, obtains Access token in return
7) Back-end (AuthController): creates JWT based on AccessToken(JwtService), authenticates user(AuthService), sends back JWT to front-end
8) Front-end (main.js): saves JWT in cookies, then redirects user to homepage