package dev.wms.usos4j.model.terms;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UsosTermsSearchParams(String query, LocalDate minFinishDate, LocalDate maxStartDate) implements UsosParams {}