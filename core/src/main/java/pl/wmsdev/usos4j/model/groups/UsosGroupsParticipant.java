package pl.wmsdev.usos4j.model.groups;

import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.terms.UsosTerm;

import java.util.List;
import java.util.Map;

public record UsosGroupsParticipant(Map<String, List<UsosGroup>> groups,
                                    List<UsosTerm> terms) implements UsosObject {}
