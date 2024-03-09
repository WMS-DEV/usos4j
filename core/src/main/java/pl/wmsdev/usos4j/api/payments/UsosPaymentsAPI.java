package pl.wmsdev.usos4j.api.payments;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.docs.NotTested;
import pl.wmsdev.usos4j.docs.NotTestedReason;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.payments.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UsosPaymentsAPI extends UsosUserAPIDefinition {

    public UsosPaymentsAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    @NotTested(reason = NotTestedReason.NO_DATA)
    public Map<?, ?> chooseInstallmentPlan(UsosChooseInstallmentPlanParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/payments/choose_installment_plan", params
        ), Map.class);
    }

    @NotTested(reason = NotTestedReason.NO_DATA)
    public UsosInstallment installment(UsosInstallmentParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/payments/installment", params
        ), UsosInstallment.class);
    }

    @NotTested(reason = NotTestedReason.NO_DATA)
    public UsosInstallmentPlan installmentPlan(UsosInstallmentPlanParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/payments/installment_plan", params
        ), UsosInstallmentPlan.class);
    }

    @NotTested(reason = NotTestedReason.NO_ACCESS)
    public UsosPayment payment(UsosPaymentParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/payments/payment", params, FieldSelector.fromRequest(UsosPayment.class)
        ), UsosPayment.class);
    }

    @NotTested(reason = NotTestedReason.NO_ACCESS)
    public UsosRemittance remittance(UsosRemittanceParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/payments/remittance", params, FieldSelector.fromRequest(UsosRemittance.class)
        ), UsosRemittance.class);
    }

    @NotTested(reason = NotTestedReason.NO_DATA)
    public List<UsosPaymentsUserAccount> userAccounts() {
        return Arrays.asList(requestWithAccessToken(requestFactory.get(
                "services/payments/user_accounts"
        ), UsosPaymentsUserAccount[].class));
    }

    public List<UsosPayment> userPayments() {
        return Arrays.asList(requestWithAccessToken(requestFactory.get(
                "services/payments/user_payments", FieldSelector.fromRequest(UsosPayment.class)
        ), UsosPayment[].class));
    }

    public List<UsosRemittance> userRemittances() {
        return Arrays.asList(requestWithAccessToken(requestFactory.get(
                "services/payments/user_remittances", FieldSelector.fromRequest(UsosRemittance.class)
        ), UsosRemittance[].class));
    }

}