package pl.wmsdev.usos4j.model.housing;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.user.UsosUser;

public record UsosResidence(String id, UsosAssignment assignment , UsosUser user,
                            UsosDormroom dormroom, UsosLocalizedString checkInDate,
                            UsosLocalizedString checkOutDate, UsosResidentType residentType) implements UsosObject {}
