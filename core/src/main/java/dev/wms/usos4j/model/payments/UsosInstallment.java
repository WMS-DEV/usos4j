package dev.wms.usos4j.model.payments;

import dev.wms.usos4j.model.common.UsosCommonObject;
import dev.wms.usos4j.model.common.UsosLocalizedString;

import java.time.LocalDate;

public record UsosInstallment(Float amount, LocalDate paymentDate, UsosLocalizedString description,
                              String installmentPlanId, Integer number) implements UsosCommonObject {}
