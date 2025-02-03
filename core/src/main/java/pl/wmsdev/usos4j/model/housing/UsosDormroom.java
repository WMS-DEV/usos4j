package pl.wmsdev.usos4j.model.housing;

import pl.wmsdev.usos4j.model.common.UsosObject;

public record UsosDormroom(String id, String number, UsosDormroomType type,
                           UsosDormitory dormitory, Integer capacity) implements UsosObject {}