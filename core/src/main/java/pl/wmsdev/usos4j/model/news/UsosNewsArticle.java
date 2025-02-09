package pl.wmsdev.usos4j.model.news;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

import java.util.Map;

public record UsosNewsArticle(String id, UsosLocalizedString author, String publicationDate, UsosLocalizedString title,
                              UsosLocalizedString headlineHtml, UsosLocalizedString contentHtml, String expiryDate,
                              String category, String faculty, Map<String, String> imageUrls,
                              UsosLocalizedString imageDescription, UsosNewsEvent event) {
    private record UsosNewsEvent(String startDate, String endDate, String city, String address, Float geoLat,
                                 Float geoLong) {
    }
}
