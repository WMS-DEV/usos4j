package dev.wms.usos4j.model.fac;

import com.fasterxml.jackson.annotation.JsonAlias;
import dev.wms.usos4j.model.common.UsosCommonObject;

public record UsosFacultiesStaticMaps(@JsonAlias("100x100") String size100x100, @JsonAlias("200x200") String size200x200,
                                      @JsonAlias("300x300") String size300x300, @JsonAlias("400x200") String size400x200,
                                      @JsonAlias("600x300") String size600x300, @JsonAlias("800x400") String size800x400,
                                      @JsonAlias("1000x250") String size1000x250) implements UsosCommonObject {}
