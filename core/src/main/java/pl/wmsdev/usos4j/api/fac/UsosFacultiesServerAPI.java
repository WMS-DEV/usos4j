package pl.wmsdev.usos4j.api.fac;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosServerAPIDefinition;
import pl.wmsdev.usos4j.docs.InProgress;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.fac.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UsosFacultiesServerAPI extends UsosServerAPIDefinition {

    public UsosFacultiesServerAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory) {
        super(oAuthService, requestFactory);
    }

    @InProgress(description = "Can't find faculty which actually has a factsheet attached to it")
    public File factsheetGet(UsosFacultiesFactsheetGetParams params) {
        return request(requestFactory.get(
                "services/fac/factsheet_get", params
        ), File.class);
    }

    public Map<String, UsosFaculty> faculties(UsosFacultiesParams params) {
        return request(requestFactory.get(
                "services/fac/faculties", params, FieldSelector.fromRequest(UsosFaculty.class)
        ), Map.class);
    }

    public UsosFaculty faculty(UsosFacultyParams params) {
        return request(requestFactory.get(
                "services/fac/faculty", params, FieldSelector.fromRequest(UsosFaculty.class)
        ), UsosFaculty.class);
    }

    public List<String> resolveFacpattern(UsosFacultiesResolveFacpatternParams params) {
        return Arrays.asList(request(requestFactory.get(
                "services/fac/resolve_facpattern", params
        ), String[].class));
    }

    public UsosFacultiesSearch search(UsosFacultiesSearchParams params) {
        return request(requestFactory.get(
                "services/fac/search", params, FieldSelector.fromRequest(UsosFaculty.class)
        ), UsosFacultiesSearch.class);
    }

    public List<String> subfacultiesDeep(UsosFacultiesSubfacultyDeepParams params) {
        return Arrays.asList(request(requestFactory.get(
                "services/fac/subfaculties_deep", params
        ), String[].class));
    }

}
