package pl.wmsdev.usos4j.api.courses;

import pl.wmsdev.usos4j.model.courses.*;

import java.util.Map;

public interface UsosCoursesServerAPI {

    Map<String, UsosClassType> classTypesIndex();

    UsosCourse course(UsosCourseParam params);

    UsosCourse2 course2(UsosCourseParam params);

    Map<String, UsosCourse> courses(UsosCoursesParam params);

    UsosCourseSearch search(UsosCourseSearchParam params);
}
