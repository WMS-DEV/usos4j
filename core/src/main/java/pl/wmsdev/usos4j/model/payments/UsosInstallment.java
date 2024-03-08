package pl.wmsdev.usos4j.model.payments;

import pl.wmsdev.usos4j.model.common.UsosCommonObject;
import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

import java.time.LocalDate;

public record UsosInstallment(Float amount, LocalDate paymentDate, UsosLocalizedString description,
                              String installmentPlanId, Integer number) implements UsosCommonObject {}
