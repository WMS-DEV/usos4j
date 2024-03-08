package dev.wms.usos4j.model.apisrv;

import dev.wms.usos4j.model.common.UsosLocalizedString;

import java.util.List;

public record UsosApiSrvInstallation(String baseUrl, String version, String machineVersion, String usosSchemaVersion,
                                     UsosLocalizedString institutionName, UsosApiSrvInstallationInstitution institution,
                                     List<String> contactEmails, String schacId, boolean mcardsSupport) {
}
