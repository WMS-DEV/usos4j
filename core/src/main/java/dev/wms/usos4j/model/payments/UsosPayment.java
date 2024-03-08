package dev.wms.usos4j.model.payments;

import dev.wms.usos4j.model.common.UsosLocalizedString;
import dev.wms.usos4j.model.common.UsosObject;
import dev.wms.usos4j.model.fac.UsosFaculty;
import dev.wms.usos4j.model.user.UsosUser;

import java.time.LocalDate;
import java.util.List;

public record UsosPayment(String id, UsosUser user, Float saldoAmount, UsosInstallmentPlan chosenInstallmentPlan, UsosUser whoChosePlan,
                          LocalDate dateOfPlanChoice, List<UsosInstallmentPlan> availableInstallmentPlans,
                          UsosPaymentsType type, UsosLocalizedString description, String state, String accountNumber,
                          LocalDate paymentDeadline, LocalDate bonusDeadline, Float bonusAmount,
                          boolean hasBonus, Integer interest, Float totalAmount, UsosPaymentsCurrency currency, UsosFaculty faculty,
                          LocalDate defaultChoiceDate) implements UsosObject {}
