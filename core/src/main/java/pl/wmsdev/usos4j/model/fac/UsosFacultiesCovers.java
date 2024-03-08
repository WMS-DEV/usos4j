package pl.wmsdev.usos4j.model.fac;

import com.fasterxml.jackson.annotation.JsonAlias;
import pl.wmsdev.usos4j.model.common.UsosCommonObject;

public record UsosFacultiesCovers(@JsonAlias("400x200") String size400x200, @JsonAlias("1000x250") String size1000x250,
                                  @JsonAlias("1000x350") String size1000x350, String screen) implements UsosCommonObject {}
