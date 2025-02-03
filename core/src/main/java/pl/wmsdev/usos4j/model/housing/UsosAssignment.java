package pl.wmsdev.usos4j.model.housing;

import com.fasterxml.jackson.annotation.JsonAlias;
import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.user.UsosUser;

import java.util.List;

public record UsosAssignment(String id, UsosUser user, UsosDormitory dormitory,
                             UsosResidentType residentType, UsosDormroomType dormroomType,
                             UsosAllocationPeriod allocationPeriod, Integer numberOfPlaces,
                             String assignedFrom, String expiryDate, @JsonAlias("can_i_extend") Boolean canIExtend,
                             @JsonAlias("why_cant_i_extend") UsosLocalizedString whyCantIExtend, UsosLocalizedString assignedTo,
                             UsosLocalizedString trusteeName, List<UsosResidence> residences) implements UsosObject {
}
