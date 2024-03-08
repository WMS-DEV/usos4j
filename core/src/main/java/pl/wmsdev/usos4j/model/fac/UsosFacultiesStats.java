package pl.wmsdev.usos4j.model.fac;

import pl.wmsdev.usos4j.model.common.UsosCommonObject;

public record UsosFacultiesStats(Integer courseCount, Integer programmeCount, Integer staffCount,
                                 Integer subfacultyCount, Integer publicSubfacultyCount) implements UsosCommonObject {}
