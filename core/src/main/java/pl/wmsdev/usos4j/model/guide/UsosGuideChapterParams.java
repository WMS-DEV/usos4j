package pl.wmsdev.usos4j.model.guide;


import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGuideChapterParams(String chapterId, UsosLanguage lang) implements UsosParams {

    public static UsosGuideChapterParamsBuilder builder(String chapterId) {
        return new UsosGuideChapterParamsBuilder().chapterId(chapterId);
    }
}
