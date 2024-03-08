package dev.wms.usos4j.model.fac;

import dev.wms.usos4j.model.common.UsosObject;

import java.util.List;

public record UsosFacultiesSearch(List<UsosFaculty> items, Boolean nextPage) implements UsosObject {}