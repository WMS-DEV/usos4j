package pl.wmsdev.usos4j.api.progs;

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
	@SuppressWarnings("unchecked")
	public Map<String, UsosProgramme> programmes(UsosProgrammesParam params) {
		return request(requestFactory.get(
						"services/progs/programmes", params,
						FieldSelector.fromRequest(UsosProgramme.class)),
				Map.class);
	}

	@Override
	public UsosProgrammeSearchResult search(UsosProgrammeSearchParam params) {
		return request(requestFactory.get(
				"services/progs/search", params,
				FieldSelector.fromRequest(UsosProgrammeSearchResult.class)),
				UsosProgrammeSearchResult.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public UsosStage stage(UsosStageParam params) {
		return request(requestFactory.get(
						"services/progs/stage", params,
						FieldSelector.fromRequest(UsosStage.class)),
				UsosStage.class);
	}

	@SuppressWarnings("unchecked")
	public List<UsosStudentProgramme> student() {
		return requestWithAccessToken(requestFactory.get(
				"services/progs/student",
				FieldSelector.fromRequest(UsosStudentProgramme.class)),
				List.class);
	}

	public UsosStudentProgramme studentProgramme(UsosStudentProgrammeParam params) {
		return requestWithAccessToken(requestFactory.get(
				"services/progs/student_programme", params,
				FieldSelector.fromRequest(UsosStudentProgramme.class)),
				UsosStudentProgramme.class);
	}

	@SuppressWarnings("unchecked")
	public Map<String, UsosStudentProgramme> studentProgrammes(UsosStudentProgrammesParam params) {
		return requestWithAccessToken(requestFactory.get(
				"services/progs/student_programmes", params,
				FieldSelector.fromRequest(UsosStudentProgramme.class)),
				Map.class);
	}
}
