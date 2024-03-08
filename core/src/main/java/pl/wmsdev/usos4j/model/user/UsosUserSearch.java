package pl.wmsdev.usos4j.model.user;


import java.util.List;

public record UsosUserSearch(List<UsosUserDataPair> items, Boolean nextPage) {
    public record UsosUserDataPair(UsosUser user, String match) {
    }
}

