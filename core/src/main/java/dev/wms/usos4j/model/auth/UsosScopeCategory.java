package dev.wms.usos4j.model.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UsosScopeCategory {
    GREEN("green"),
    YELLOW("yellow"),
    RED("red");

    @Getter
    private final String accessLevel;
}
