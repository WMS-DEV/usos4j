package pl.wmsdev.usos4j.model.payments;

import pl.wmsdev.usos4j.model.common.UsosCommonObject;

import java.util.List;

public record UsosInstallmentPlan(String id, Boolean isDefault, UsosPaymentsCurrency currency, List<UsosInstallment> installments) implements UsosCommonObject {}