package pl.wmsdev;

import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.guide.*;

public class UsosGuideAPITest extends UsosAPIBaseTest {
    @Test
    void guideGuide() {
        log(userApi.guide().guide(UsosGuideGuideParams.builder(UsosLanguage.PL).build()));
    }

    @Test
    void guideChapter() {
        log(userApi.guide().chapter(UsosGuideChapterParams.builder("1").build()));
    }

    @Test
    void guideChapters() {
        log(userApi.guide().chapters(UsosGuideChaptersParams.builder("1|2").build()));
    }

    @Test
    void guideEntry() {
        log(userApi.guide().entry(UsosGuideEntryParams.builder("1").build()));
    }

    @Test
    void guideEntries() {
        log(userApi.guide().entries(UsosGuideEntriesParams.builder("1|2").build()));
    }

    @Test
    void guidePage() {
        log(userApi.guide().page(UsosGuidePageParams.builder("1").build()));
    }


    @Test
    void guidePages() {
        log(userApi.guide().pages(UsosGuidePagesParams.builder("1").build()));
    }
}