package pl.wmsdev.springsecuritydemo.auth;

public record AccessRequest(String requestToken, String tokenSecret, String verifier) {}
