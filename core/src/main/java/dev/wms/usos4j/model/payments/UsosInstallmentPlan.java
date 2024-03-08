package dev.wms.usos4j.model.payments;

import dev.wms.usos4j.model.common.UsosCommonObject;

import java.util.List;

public record UsosInstallmentPlan(String id, boolean isDefault, UsosPaymentsCurrency currency, List<UsosInstallment> installments) implements UsosCommonObject {}