package pl.wmsdev.usos4j.model.housing;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;

public record UsosAllocationPeriod(String id, String termId, UsosLocalizedString statusName,
                                   UsosLocalizedString statusDesc, String startDate,
                                   String endDate) implements UsosObject {
}
