package pl.wmsdev.usos4j.model.terms;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;

import java.time.LocalDate;

public record UsosTerm(String id, UsosLocalizedString name, LocalDate startDate, LocalDate endDate, Integer orderKey,
                       LocalDate finishDate, Boolean isActive) implements UsosObject {}
