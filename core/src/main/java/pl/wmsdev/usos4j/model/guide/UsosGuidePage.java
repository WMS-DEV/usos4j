package pl.wmsdev.usos4j.model.guide;


import pl.wmsdev.usos4j.model.common.UsosCommonObject;
import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

import java.util.List;

public record UsosGuidePage(String id,
                            UsosLocalizedString title,
                            List<UsosGuideEntry> entries,
                            UsosGuideChapterCommon chapter) implements UsosCommonObject {
}
