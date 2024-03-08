package pl.wmsdev.usos4j.model.payments;

import pl.wmsdev.usos4j.docs.NotTested;
import pl.wmsdev.usos4j.docs.NotTestedReason;
import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.fac.UsosFaculty;

@NotTested(reason = NotTestedReason.NO_DATA)
public record UsosPaymentsUserAccount(String accountNumber, String name, String titlePrefix,
                                      UsosFaculty faculty, UsosPaymentsCurrency currency, String swift) implements UsosObject {}
