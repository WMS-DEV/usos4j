package dev.wms;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class UsosFeedbackAPITest extends UsosAPIBaseTest {
    @Test
    void status() {
        log(serverApi.feedback().status());
    }

    @Disabled("PWr feedback moodule is not enabled yet, so this function will throw error")
    @Test
    void reportDb() {
        log(userApi.feedback().reportDb("{'title':'test','value':'test'}", "{'comment':'test'}"));
    }
}