package pl.wmsdev.usos4j.model.courses;

import pl.wmsdev.usos4j.model.common.UsosCommonObject;
import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.terms.UsosTerm;

import java.util.List;
import java.util.Map;

public record UsosCourse(String id, UsosLocalizedString name, String homepageUrl,
                         String profileUrl, Boolean isCurrentlyConducted, Map<String, String>[] terms,
                         String facId, String langId, Float ects_credits_simplified) implements UsosCommonObject {}
