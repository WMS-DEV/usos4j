package pl.wmsdev.usos4j.model.fac;

import com.fasterxml.jackson.annotation.JsonAlias;
import pl.wmsdev.usos4j.model.common.UsosCommonObject;

public record UsosFacultiesLogos(@JsonAlias("50x50") String size50x50, @JsonAlias("100x100") String size100x100,
                                 @JsonAlias("200x200") String size200x200, String screen) implements UsosCommonObject {}
