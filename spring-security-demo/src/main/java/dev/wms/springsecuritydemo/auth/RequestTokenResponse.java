package dev.wms.springsecuritydemo.auth;

public record RequestTokenResponse(String requestToken, String tokenSecret, String authorizationUrl) {}
