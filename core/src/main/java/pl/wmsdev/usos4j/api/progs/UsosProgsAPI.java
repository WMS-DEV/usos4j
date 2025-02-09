package pl.wmsdev.usos4j.api.progs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.progs.*;

import java.util.List;
import java.util.Map;

public class UsosProgsAPI extends UsosUserAPIDefinition implements UsosProgsServerAPI {

    public UsosProgsAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    @Override
    public UsosProgramme programme(UsosProgrammeParam params) {
        return request(requestFactory.get(
                        "services/progs/programme", params,
                        FieldSelector.fromRequest(UsosProgramme.class)),
                UsosProgramme.class);
    }

    @Override
    public Map<String, UsosProgramme> programmes(UsosProgrammesParam params) {
        return request(requestFactory.get(
                        "services/progs/programmes", params,
                        FieldSelector.fromRequest(UsosProgramme.class)),
                new TypeReference<>() {});
    }

    @Override
    public UsosProgrammeSearchResult search(UsosProgrammeSearchParam params) {
        return request(requestFactory.get(
                        "services/progs/search", params,
                        FieldSelector.fromRequest(UsosProgrammeSearchResult.class)),
                UsosProgrammeSearchResult.class);
    }

    @Override
    public UsosStage stage(UsosStageParam params) {
        return request(requestFactory.get(
                        "services/progs/stage", params,
                        FieldSelector.fromRequest(UsosStage.class)),
                UsosStage.class);
    }

    public List<UsosStudentProgramme> student() {
        return List.of(requestWithAccessToken(requestFactory.get(
                        "services/progs/student",
                        FieldSelector.fromRequest(UsosStudentProgramme.class)),
                UsosStudentProgramme[].class));
    }

    public UsosStudentProgramme studentProgramme(UsosStudentProgrammeParam params) {
        return requestWithAccessToken(requestFactory.get(
                        "services/progs/student_programme", params,
                        FieldSelector.fromRequest(UsosStudentProgramme.class)),
                UsosStudentProgramme.class);
    }

    public Map<String, UsosStudentProgramme> studentProgrammes(UsosStudentProgrammesParam params) {
        return requestWithAccessToken(requestFactory.get(
                        "services/progs/student_programmes", params,
                        FieldSelector.fromRequest(UsosStudentProgramme.class)),
                new TypeReference<>() {});
    }
}
