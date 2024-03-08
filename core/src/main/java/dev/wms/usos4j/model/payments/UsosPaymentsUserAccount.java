package dev.wms.usos4j.model.payments;

import dev.wms.usos4j.docs.NotTested;
import dev.wms.usos4j.docs.NotTestedReason;
import dev.wms.usos4j.model.common.UsosObject;
import dev.wms.usos4j.model.fac.UsosFaculty;

@NotTested(reason = NotTestedReason.NO_DATA)
public record UsosPaymentsUserAccount(String accountNumber, String name, String titlePrefix,
                                      UsosFaculty faculty, UsosPaymentsCurrency currency, String swift) implements UsosObject {}
