package dev.wms;

import dev.wms.usos4j.model.terms.*;
import org.junit.jupiter.api.Test;

public class UsosTermsAPITest extends UsosAPIBaseTest {

    @Test
    public void search() {
        log(serverApi.terms().search(UsosTermsSearchParams.builder()
                        .query("2021")
                        .build())
        );
    }

    @Test
    public void term() {
        log(serverApi.terms().term(UsosTermParams.builder("2022/23-Z").build()));
    }

    @Test
    public void terms() {
        log(serverApi.terms().terms(UsosTermsParams.builder("2022/23-Z", "2022/23", "2023/24-L").build()));
    }

    @Test
    public void termsIndex() {
        log(serverApi.terms().termsIndex(UsosTermIndexParams.builder().termType(UsosTermType.YEAR).build()));
    }
}
