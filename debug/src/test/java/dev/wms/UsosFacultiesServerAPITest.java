package dev.wms;

import dev.wms.usos4j.model.common.UsosLanguage;
import dev.wms.usos4j.model.fac.*;
import org.junit.jupiter.api.Test;

public class UsosFacultiesServerAPITest extends UsosAPIBaseTest {

    @Test
    void faculties() {
        log(serverApi.fac().faculties(UsosFacultiesParams.builder("W4N").build()));
    }

    @Test
    void faculty() {
        log(serverApi.fac().faculty(UsosFacultyParams.builder("W4N").build()));
    }

    @Test
    void search() {
        log(serverApi.fac().search(UsosFacultiesSearchParams.builder(UsosLanguage.PL, "").build()));
    }

    @Test
    void resolveFacpattern() {
        log(serverApi.fac().resolveFacpattern(UsosFacultiesResolveFacpatternParams.builder().addAllAncestors("W4N").build()));
    }

    @Test
    void subfacultiesDeep() {
        log(serverApi.fac().subfacultiesDeep(UsosFacultiesSubfacultyDeepParams.builder("W4N").build()));
    }

}