package dev.wms.springsecuritydemo.auth;

public record AccessRequest(String requestToken, String tokenSecret, String verifier) {}
