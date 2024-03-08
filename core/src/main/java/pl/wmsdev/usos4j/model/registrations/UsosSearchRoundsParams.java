package pl.wmsdev.usos4j.model.registrations;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UsosSearchRoundsParams (LocalDate startDate, LocalDate endDate, String facultyId, Boolean userRelated) implements UsosParams {

    public static UsosSearchRoundsParamsBuilder builder(LocalDate startDate, LocalDate endDate) {
        return new UsosSearchRoundsParamsBuilder().startDate(startDate).endDate(endDate);
    }

}
