package pl.wmsdev.usos4j.model.payments;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.fac.UsosFacultySimple;
import pl.wmsdev.usos4j.model.user.UsosUser;

import java.time.LocalDate;

public record UsosRemittance(String id, UsosUser user, Integer remaining, UsosLocalizedString description,
                             Float amount, String status, Boolean isSettled,
                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate date,
                             UsosPaymentsCurrency currency, UsosFacultySimple faculty) implements UsosObject {}
