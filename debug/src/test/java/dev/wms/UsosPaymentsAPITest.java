package dev.wms;

import dev.wms.usos4j.model.payments.*;
import org.junit.jupiter.api.Test;

public class UsosPaymentsAPITest extends UsosAPIBaseTest {

    @Test
    void payment() {
        userApi.payments().userPayments().stream().findFirst().ifPresent(
                payment ->log(userApi.payments().payment(UsosPaymentParams.builder(payment.id()).build()))
        );
    }

    @Test
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
