package pl.wmsdev.usos4j.model.geo;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.fac.UsosFaculty;
import pl.wmsdev.usos4j.model.phones.UsosPhonesPhoneNumber;

import java.util.List;
import java.util.Map;

public record UsosGeoBuilding2(
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
        UsosGeoStaticMaps staticMapUrls,
        List<UsosFaculty> relatedFaculties,
        List<UsosPhonesPhoneNumber> allPhoneNumbers,
        List<UsosGeoRoom> rooms

) implements UsosObject {
}
