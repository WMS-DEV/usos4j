package pl.wmsdev;

import org.junit.jupiter.api.Test;

public class UsosCreditsAPITest extends UsosAPIBaseTest {
    @Test
    void usedSum() {
        log(userApi.credits().usedSum());
        log(userApi.credits().usedSum("6")); //nie moge znaleźć wartosci zeby to przetestować xd
    }
}
