package dev.wms.usos4j.model.user;

import java.util.List;

public record UsosUserUsersIndex(List<UsosUser> users, Boolean nextPage, Integer total) {
}
