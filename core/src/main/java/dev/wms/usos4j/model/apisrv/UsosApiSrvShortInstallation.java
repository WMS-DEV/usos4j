package dev.wms.usos4j.model.apisrv;

import dev.wms.usos4j.model.common.UsosLocalizedString;

import java.util.List;

public record UsosApiSrvShortInstallation(String baseUrl, String version, UsosLocalizedString institutionName,
                                          List<String> contactEmails) {
}
