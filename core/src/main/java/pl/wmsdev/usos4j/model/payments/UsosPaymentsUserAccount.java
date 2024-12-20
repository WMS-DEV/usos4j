package pl.wmsdev.usos4j.model.payments;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.fac.UsosFacultySimple;

public record UsosPaymentsUserAccount(String accountNumber, UsosLocalizedString name, String titlePrefix,
                                      UsosFacultySimple faculty, UsosPaymentsCurrency currency, String swift) implements UsosObject {}
