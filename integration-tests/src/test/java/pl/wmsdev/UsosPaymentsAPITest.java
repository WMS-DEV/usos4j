package pl.wmsdev;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.payments.UsosPaymentParams;
import pl.wmsdev.usos4j.model.payments.UsosRemittanceParams;

public class UsosPaymentsAPITest extends UsosAPIBaseTest {

    @Test
    @Disabled("PWr denies access")
    void payment() {
        log(userApi.payments().userPayments().stream().findFirst());
        userApi.payments().userPayments().stream().findFirst().ifPresent(
                payment ->log(userApi.payments().payment(UsosPaymentParams.builder(payment.id()).build()))
        );
    }

    @Test
    @Disabled("PWr denies access")
    void remittance() {
        userApi.payments().userRemittances().stream().findFirst().ifPresent(
                remittance -> log(userApi.payments().remittance(UsosRemittanceParams.builder(remittance.id()).build()))
        );
    }

    @Test
    void userAccounts() {
        log(userApi.payments().userAccounts());
    }

    @Test
    void userPayments() {
        log(userApi.payments().userPayments());
    }

    @Test
    void userRemittances() {
        log(userApi.payments().userRemittances());
    }

}
