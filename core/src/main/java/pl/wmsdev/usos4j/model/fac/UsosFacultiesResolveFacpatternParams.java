package pl.wmsdev.usos4j.model.fac;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import pl.wmsdev.usos4j.model.common.UsosParams;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public record UsosFacultiesResolveFacpatternParams(String facpattern) implements UsosParams {

    public static UsosFacultiesResolveFacpatternParamsBuilder builder(String facpattern) {
        return new UsosFacultiesResolveFacpatternParamsBuilder().facpattern(facpattern);
    }

    public static UsosFacultiesResolveFacpatternParamsBuilder builder() {
        return new UsosFacultiesResolveFacpatternParamsBuilder();
    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    public static class UsosFacultiesResolveFacpatternParamsBuilder {

        private String facpattern = "";

        public UsosFacultiesResolveFacpatternParamsBuilder addOnlyThis(String facId) {
            facpattern += (facpattern.equals("") ? "" : "|") + "=" + facId;
            return this;
        }

        public UsosFacultiesResolveFacpatternParamsBuilder addAllDescendants(String facId) {
            facpattern += (facpattern.equals("") ? "" : "|") + "+" + facId;
            return this;
        }

        public UsosFacultiesResolveFacpatternParamsBuilder addAllAncestors(String facId) {
            facpattern += (facpattern.equals("") ? "" : "|") + "<" + facId;
            return this;
        }

        public UsosFacultiesResolveFacpatternParams build() {
            return new UsosFacultiesResolveFacpatternParams(URLEncoder.encode(facpattern, StandardCharsets.UTF_8));
        }
    }
}