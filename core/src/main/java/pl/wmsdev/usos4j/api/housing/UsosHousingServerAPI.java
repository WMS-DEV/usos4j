package pl.wmsdev.usos4j.api.housing;

import pl.wmsdev.usos4j.model.housing.*;

import java.util.List;
import java.util.Map;

public interface UsosHousingServerAPI {
    List<UsosAllocationPeriod> allocationPeriodIndex();

    Map<String, UsosAllocationPeriod> allocationPeriods(UsosAllocationPeriodsParam params);

    Map<String, UsosDormitory> dormitories(UsosDormitoriesParam params);

    UsosDormitory dormitory(UsosDormitoryParam params);

    List<UsosDormitory> dormitoryIndex();

    UsosDormroomType dormroomType(UsosDormroomTypeParam params);

    List<UsosDormroomType> dormroomTypeIndex();

    Map<String, UsosDormroomType> dormroomTypes(UsosDormroomTypesParam params);

    UsosResidence residentType(UsosResidentTypeParam params);

    List<UsosResidentType> residentTypeIndex();

    Map<String, UsosResidentType> residentTypes(UsosResidentTypesParam params);

    UsosAllocationPeriod allocationPeriod(UsosAllocationPeriodParam params);
}
