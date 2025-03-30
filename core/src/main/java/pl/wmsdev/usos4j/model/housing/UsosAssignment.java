package pl.wmsdev.usos4j.model.housing;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.user.UsosUser;

import java.util.List;

public record UsosAssignment(String id, UsosUser user, UsosDormitory dormitory,
                             UsosResidentType residentType, UsosDormroomType dormroomType,
                             UsosAllocationPeriod allocationPeriod, Integer numberOfPlaces,
                             String assignedFrom, String expiryDate, Boolean canIExtend,
                             UsosLocalizedString whyCantIExtend, UsosLocalizedString assignedTo,
                             UsosLocalizedString trusteeName, List<UsosResidence> residences) implements UsosObject {
}
