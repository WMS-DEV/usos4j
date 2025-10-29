package pl.wmsdev.usos4j.model.guide;

import pl.wmsdev.usos4j.model.common.UsosCommonObject;
import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

import java.util.Map;

public record UsosGuideEntry(String id, UsosLocalizedString title, UsosLocalizedString content,
                             UsosGuidePageCommon page, Map<String, String> imageUrls) implements UsosCommonObject {
}