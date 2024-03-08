package pl.wmsdev.usos4j.model.registrations;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;

public record UsosTokenRegistrationRound(String id, UsosLocalizedString name, String startDate, String endDate,
                                         Integer selectionLimit, Boolean isDedicated, Boolean isExchange,
                                         Boolean isOnlyEntitled, String microRoundLength, String microBreakLength,
                                         Boolean isPrioritized, String firstRoundScale) implements UsosObject {
}
