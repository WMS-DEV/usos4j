package pl.wmsdev.usos4j.model.terms;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

import java.time.LocalDate;

@Builder
public record UsosTermsSearchParams(String query, LocalDate minFinishDate,
                                    LocalDate maxStartDate) implements UsosParams {}