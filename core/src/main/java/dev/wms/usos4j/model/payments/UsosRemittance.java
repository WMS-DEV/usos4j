package dev.wms.usos4j.model.payments;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.wms.usos4j.model.common.UsosLocalizedString;
import dev.wms.usos4j.model.common.UsosObject;
import dev.wms.usos4j.model.fac.UsosFaculty;
import dev.wms.usos4j.model.user.UsosUser;

import java.time.LocalDate;

public record UsosRemittance(String id, UsosUser user, Integer remaining, UsosLocalizedString description,
                             Float amount, String status, boolean isSettled,
                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate date,
                             UsosPaymentsCurrency currency, UsosFaculty faculty) implements UsosObject {}
