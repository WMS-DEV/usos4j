package dev.wms.usos4j.model.auth;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

public final class UsosScopes {

    private static Collection<UsosScope> filterScopes(Predicate<UsosScope> predicate) {
        return Arrays.stream(UsosScope.values())
                .filter(predicate)
                .toList();
    }

    public static Collection<UsosScope> allScopes() {
        return filterScopes(scope -> true);
    }

    public static Collection<UsosScope> allStudent() {
        return filterScopes(scope -> !scope.isAdminOnly());
    }

    public static Collection<UsosScope> allAdmin() {
        return filterScopes(UsosScope::isAdminOnly);
    }

    public static Collection<UsosScope> allGreen() {
        return filterScopes(scope -> scope.getCategory().equals(UsosScopeCategory.GREEN));
    }

    public static Collection<UsosScope> allYellow() {
        return filterScopes(scope -> scope.getCategory().equals(UsosScopeCategory.YELLOW));
    }

    public static Collection<UsosScope> allRed() {
        return filterScopes(scope -> scope.getCategory().equals(UsosScopeCategory.RED));
    }

    public static Collection<UsosScope> categories(UsosScopeCategory... categories) {
        return filterScopes(scope -> Arrays.asList(categories).contains(scope.getCategory()));
    }
}
