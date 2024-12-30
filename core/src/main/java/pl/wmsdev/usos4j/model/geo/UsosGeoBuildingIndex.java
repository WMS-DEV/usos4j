package pl.wmsdev.usos4j.model.geo;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;

import java.util.List;
import java.util.Map;

public record UsosGeoBuildingIndex(
        String id,
        UsosLocalizedString name,
        String profileUrl,
        String postalAddress,
        UsosLocalizedString campusName,
        String hrId,
        UsosGeoLocation location,
        Map<String, String> markerStyle,
        List<String> phoneNumbers,
        Map<String, String> photoUrls,
        UsosGeoStaticMaps staticMapUrls
) implements UsosObject {
}
