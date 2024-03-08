package pl.wmsdev.usos4j.model.fac;

import pl.wmsdev.usos4j.model.common.UsosObject;

import java.util.List;

public record UsosFacultiesSearch(List<UsosFaculty> items, Boolean nextPage) implements UsosObject {}