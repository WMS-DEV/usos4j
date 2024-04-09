package pl.wmsdev.usos4j.model.courses;

import pl.wmsdev.usos4j.model.common.UsosObject;

import java.util.List;

public record UsosCourseSearch(List<UsosCourse> items, boolean nextPage) implements UsosObject {
}
