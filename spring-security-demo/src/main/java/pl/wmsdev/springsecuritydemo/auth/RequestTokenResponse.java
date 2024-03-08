package pl.wmsdev.springsecuritydemo.auth;

public record RequestTokenResponse(String requestToken, String tokenSecret, String authorizationUrl) {}
