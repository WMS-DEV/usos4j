package pl.wmsdev.usos4j.model.payments;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.fac.UsosFacultySimple;
import pl.wmsdev.usos4j.model.user.UsosUser;

import java.time.LocalDate;
import java.util.List;

public record UsosPayment(String id, UsosUser user, Float saldoAmount, UsosInstallmentPlan chosenInstallmentPlan,
                          UsosUser whoChosePlan,
                          LocalDate dateOfPlanChoice, List<UsosInstallmentPlan> availableInstallmentPlans,
                          UsosPaymentsType type, UsosLocalizedString description, String state, String accountNumber,
                          LocalDate paymentDeadline, LocalDate bonusDeadline, Float bonusAmount,
                          Boolean hasBonus, Integer interest, Float totalAmount, UsosPaymentsCurrency currency,
                          UsosFacultySimple faculty,
                          LocalDate defaultChoiceDate) implements UsosObject {}
