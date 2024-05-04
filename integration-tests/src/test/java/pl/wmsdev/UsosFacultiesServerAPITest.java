package pl.wmsdev;

import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.fac.UsosFacultiesResolveFacpatternParams;
import pl.wmsdev.usos4j.model.fac.UsosFacultiesSearchParams;
import pl.wmsdev.usos4j.model.fac.UsosFacultiesSubfacultyDeepParams;
import pl.wmsdev.usos4j.model.fac.UsosFacultyParams;

import static pl.wmsdev.usos4j.model.fac.UsosFacultiesParams.builder;

public class UsosFacultiesServerAPITest extends UsosAPIBaseTest {

    @Test
    void faculties() {
        log(serverApi.fac().faculties(builder("W4N", "W5", "W11").build()).get("W4N").name());
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