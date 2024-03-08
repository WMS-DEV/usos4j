package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosLocalizedString;
import dev.wms.usos4j.model.common.UsosObject;

import java.time.LocalDate;

public record UsosRegistrationTerm(String id, UsosLocalizedString name, LocalDate dateFrom, LocalDate dateTo,
                                   Integer orderKey, Boolean isActive) implements UsosObject {}