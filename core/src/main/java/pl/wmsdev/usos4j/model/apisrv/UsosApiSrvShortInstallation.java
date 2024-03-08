package pl.wmsdev.usos4j.model.apisrv;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

import java.util.List;

public record UsosApiSrvShortInstallation(String baseUrl, String version, UsosLocalizedString institutionName,
                                          List<String> contactEmails) {
}
