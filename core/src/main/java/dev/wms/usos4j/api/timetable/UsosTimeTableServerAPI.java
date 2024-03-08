package dev.wms.usos4j.api.timetable;

import dev.wms.usos4j.model.timetable.UsosTimeTableActivity;

import java.time.LocalDate;
import java.util.List;

public interface UsosTimeTableServerAPI {
    List<UsosTimeTableActivity> staff(Integer userId);

    List<UsosTimeTableActivity> staff(Integer userId, Integer days);

    List<UsosTimeTableActivity> staff(Integer userId, LocalDate start, Integer days);

    List<UsosTimeTableActivity> courseEdition(String courseId, String termId, LocalDate start, int days);

    List<UsosTimeTableActivity> courseEdition(String courseId, String termId, LocalDate start);

    List<UsosTimeTableActivity> courseEdition(String courseId, String termId, int days);

    List<UsosTimeTableActivity> courseEdition(String courseId, String termId);

    List<UsosTimeTableActivity> classgroup(String unitId, String groupNumber, LocalDate start, int days);

    List<UsosTimeTableActivity> classgroup(String unitId, String groupNumber, LocalDate start);

    List<UsosTimeTableActivity> classgroup(String unitId, String groupNumber, int days);

    List<UsosTimeTableActivity> classgroup(String unitId, String groupNumber);

    List<LocalDate> classgroupDates(String unitId, String groupNumber);

    List<UsosTimeTableActivity> classgroupDatesTwo(String unitId, String groupNumber);

    List<UsosTimeTableActivity> classgroups(List<String> classgroupIds, boolean partial, LocalDate start, int days);

    List<UsosTimeTableActivity> classgroups(List<String> classgroupIds, boolean partial, LocalDate start);

    List<UsosTimeTableActivity> classgroups(List<String> classgroupIds, boolean partial, int days);

    List<UsosTimeTableActivity> classgroups(List<String> classgroupIds);

    List<UsosTimeTableActivity> courseEditions(String courseEditionIds, boolean partial, LocalDate start, int days);

    List<UsosTimeTableActivity> courseEditions(String courseEditionIds, boolean partial, LocalDate start);

    List<UsosTimeTableActivity> courseEditions(String courseEditionIds, boolean partial, int days);

    List<UsosTimeTableActivity> courseEditions(String courseEditionIds);
}
