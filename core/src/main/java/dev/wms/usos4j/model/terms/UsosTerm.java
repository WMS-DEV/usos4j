package dev.wms.usos4j.model.terms;

import dev.wms.usos4j.model.common.UsosLocalizedString;
import dev.wms.usos4j.model.common.UsosObject;

import java.time.LocalDate;

public record UsosTerm(String id, UsosLocalizedString name, LocalDate startDate, LocalDate endDate, Integer orderKey,
                       LocalDate finishDate, Boolean isActive) implements UsosObject {}
