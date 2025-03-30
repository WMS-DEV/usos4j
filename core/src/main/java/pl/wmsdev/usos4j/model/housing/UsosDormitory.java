package pl.wmsdev.usos4j.model.housing;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;

import java.util.List;

public record UsosDormitory(String id, UsosLocalizedString name, UsosLocalizedString shortName,
                            Boolean canIManage, List<UsosDormroom> dormrooms) implements UsosObject {}
