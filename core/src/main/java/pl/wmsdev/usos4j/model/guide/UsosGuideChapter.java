package pl.wmsdev.usos4j.model.guide;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;

import java.util.List;

public record UsosGuideChapter(String id, UsosLocalizedString title, List<UsosGuidePage> pages) implements UsosObject {
}
