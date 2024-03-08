package dev.wms.usos4j.model.fac;

import dev.wms.usos4j.model.common.UsosCommonObject;

public record UsosFacultiesStats(Integer courseCount, Integer programmeCount, Integer staffCount,
                                 Integer subfacultyCount, Integer publicSubfacultyCount) implements UsosCommonObject {}
