package pl.wmsdev.usos4j.model.guide;

import pl.wmsdev.usos4j.model.common.UsosCommonObject;
import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

public record UsosGuidePageCommon(String id,
                                  UsosLocalizedString title) implements UsosCommonObject {
}
