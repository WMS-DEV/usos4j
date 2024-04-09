package pl.wmsdev.usos4j.api.grades;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.docs.NotTested;
import pl.wmsdev.usos4j.docs.NotTestedReason;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.grades.*;

import java.util.List;
import java.util.Map;

public class UsosGradesAPI extends UsosUserAPIDefinition implements UsosGradesServerAPI {

    public UsosGradesAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    public UsosGradesCourseEdition courseEdition2(UsosGradesCourseEditionParam params) {
        return requestWithAccessToken(requestFactory.get(
                "services/grades/course_edition2", params,
                FieldSelector.fromRequest(UsosGrade.class)
        ), UsosGradesCourseEdition.class);
    }

    @SuppressWarnings("unchecked")
    public Map<Integer, UsosGrade> exam(UsosGradesExamParam params) {
        return requestWithAccessToken(requestFactory.get(
                "services/grades/exam", params,
                FieldSelector.fromRequest(UsosGrade.class)
        ), Map.class);
    }

    public UsosGrade grade(UsosGradeParam params) {
        return requestWithAccessToken(requestFactory.get(
                "services/grades/grade", params,
                FieldSelector.fromRequest(UsosGrade.class)
        ), UsosGrade.class);
    }

    @Override
    public UsosGradeType gradeType(UsosGradeTypeParam params) {
        return request(requestFactory.get(
                "services/grades/grade_type", params,
                FieldSelector.fromRequest(UsosGradeType.class)
        ), UsosGradeType.class);
    }

    @Override
    public List<UsosGradeType> gradeTypeIndex() {
        return List.of(request(requestFactory.get(
                "services/grades/grade_type_index",
                FieldSelector.fromRequest(UsosGradeType.class)
        ), UsosGradeType[].class));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, UsosGradeType> gradeTypes(UsosGradeTypesParam params) {
        return request(requestFactory.get(
                "services/grades/grade_types", params,
                FieldSelector.fromRequest(UsosGradeType.class)
        ), Map.class);
    }

    @NotTested(reason = NotTestedReason.NO_ACCESS)
    public List<UsosGrade> latest(UsosLatestParam params) {
        return List.of(requestWithAccessToken(requestFactory.get(
                "/services/grades/latest", params,
                FieldSelector.fromRequest(UsosGrade.class)
        ), UsosGrade[].class));
    }

    @SuppressWarnings("unchecked")
    public Map<String, Map<String, UsosGradesCourseEdition>> terms2(UsosGradesTermsParam params) {
        return requestWithAccessToken(requestFactory.get(
                "services/grades/terms2", params,
                FieldSelector.fromRequest(UsosGrade.class)
        ), Map.class);
    }
}
