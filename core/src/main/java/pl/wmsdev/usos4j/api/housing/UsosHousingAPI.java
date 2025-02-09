package pl.wmsdev.usos4j.api.housing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.docs.Beta;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.housing.*;

import java.util.List;
import java.util.Map;

public class UsosHousingAPI extends UsosUserAPIDefinition implements UsosHousingServerAPI {

    public UsosHousingAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    @Beta
    public Map<String, UsosDormitory> dormitories(UsosDormitoriesParam params) {
        return requestWithAccessToken(requestFactory.get(
                "services/housing/dormitories", params,
                FieldSelector.fromRequest(UsosDormitory.class)
        ), new TypeReference<>() {});
    }

    @Beta
    public UsosDormroomType dormroomType(UsosDormroomTypeParam params) {
        return request(requestFactory.get(
                "services/housing/dormroom_type", params,
                FieldSelector.fromRequest(UsosDormroomType.class)
        ), UsosDormroomType.class);
    }


    @Beta
    public UsosResidence residentType(UsosResidentTypeParam params) {
        return request(requestFactory.get(
                "services/housing/resident_type", params,
                FieldSelector.fromRequest(UsosResidentType.class)
        ), UsosResidence.class);
    }

    @Beta
    public List<UsosDormroomType> dormroomTypeIndex() {
        return request(requestFactory.get(
                "services/housing/dormroom_type_index",
                FieldSelector.fromRequest(UsosDormroomType.class)
        ), new TypeReference<>() {});
    }

    @Beta
    public Map<String, UsosAllocationPeriod> allocationPeriods(UsosAllocationPeriodsParam params) {
        return requestWithAccessToken(requestFactory.get(
                "services/housing/allocation_periods", params,
                FieldSelector.fromRequest(UsosAllocationPeriod.class)
        ), new TypeReference<>() {});
    }

    @Beta
    public Map<String, UsosDormroomType> dormroomTypes(UsosDormroomTypesParam params) {
        return request(requestFactory.get(
                "services/housing/dormroom_types", params,
                FieldSelector.fromRequest(UsosDormroomType.class)
        ), new TypeReference<>() {});
    }

    @Beta
    public Map<String, UsosResidentType> residentTypes(UsosResidentTypesParam params) {
        return request(requestFactory.get(
                "services/housing/resident_types", params,
                FieldSelector.fromRequest(UsosResidentType.class)
        ), new TypeReference<>() {});
    }

    @Beta
    public UsosDormitory dormitory(UsosDormitoryParam params) {
        return requestWithAccessToken(requestFactory.get(
                "services/housing/dormitory", params,
                FieldSelector.fromRequest(UsosDormitory.class)
        ), UsosDormitory.class);
    }

    @Beta
    public List<UsosDormitory> dormitoryIndex() {
        return requestWithAccessToken(requestFactory.get(
                "services/housing/dormitory_index",
                FieldSelector.fromRequest(UsosDormitory.class)
        ), new TypeReference<>() {});
    }

    @Beta
    public List<UsosResidentType> residentTypeIndex() {
        return requestWithAccessToken(requestFactory.get(
                "services/housing/resident_type_index",
                FieldSelector.fromRequest(UsosResidentType.class)
        ), new TypeReference<>() {});
    }

    @Beta
    public List<UsosAllocationPeriod> allocationPeriodIndex() {
        return requestWithAccessToken(requestFactory.get(
                "services/housing/allocation_period_index",
                FieldSelector.fromRequest(UsosAllocationPeriod.class)
        ), new TypeReference<>() {});
    }

    @Beta
    public UsosAllocationPeriod allocationPeriod(UsosAllocationPeriodParam params) {
        return request(requestFactory.get(
                "services/housing/allocation_period", params,
                FieldSelector.fromRequest(UsosAllocationPeriod.class)
        ), UsosAllocationPeriod.class);
    }
}
