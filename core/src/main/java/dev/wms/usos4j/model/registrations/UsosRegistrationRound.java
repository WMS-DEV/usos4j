package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosLocalizedString;
import dev.wms.usos4j.model.common.UsosObject;

import java.time.LocalDate;

public record UsosRegistrationRound (String id, UsosLocalizedString name, UsosStatus status, UsosRegistrationMode registrationMode,
                                     LocalDate startDate, LocalDate endDate, Integer selectionLimit,
                                     Boolean isDedicated, Boolean isOverflowAllowed, Boolean isExchange,
                                     Boolean isOnlyEntitled, String rankCode, UsosRanking ranking, Boolean isProcessed) implements UsosObject {
}
