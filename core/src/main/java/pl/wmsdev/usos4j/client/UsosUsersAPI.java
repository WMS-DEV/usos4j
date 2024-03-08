package pl.wmsdev.usos4j.client;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.docs.Beta;
import pl.wmsdev.usos4j.docs.Deprecated;
import pl.wmsdev.usos4j.docs.InProgress;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.user.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UsosUsersAPI extends UsosUserAPIDefinition {


    public UsosUsersAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory,
                        UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    @InProgress(description = "always return message: {message: Nonce already used}")
    public UsosUserShort user2() {
        return requestWithAccessToken(requestFactory.get("services/users/user2", Map.of("fields",
                        List.of("id", "first_name", "student_number", "middle_names", "last_name", "student_status", "staff_status"))),
                UsosUserShort.class);
    }

    public UsosUserEmploymentGroup employmentGroup(String id) {
        return requestWithAccessToken(requestFactory.get("services/users/employment_group", Map.of("fields",
                List.of("id", "name", "university_teachers"), "id", List.of(id))), UsosUserEmploymentGroup.class);
    }

    @Deprecated
    public Object photo() {
        return requestWithAccessToken(requestFactory.get("services/users/photo"));
    }

    public List<UsosUserEmploymentFunction> employmentFunctions() {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/users/employment_functions", Map.of("fields",
                List.of("function", "faculty", "is_official"))), UsosUserEmploymentFunction[].class));
    }

    public List<UsosUserEmploymentFunction> employmentFunctions(String userId) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/users/employment_functions", Map.of("fields",
                List.of("function", "faculty", "is_official"), "user_id", List.of(userId))), UsosUserEmploymentFunction[].class));
    }

    @Deprecated
    public UsosUserTeacher searchCurrentTeachers() {
        return requestWithAccessToken(requestFactory.get("services/users/search_current_teachers",
                Map.of("num", List.of("20"))), UsosUserTeacher.class);
    }

    @Deprecated
    public UsosUserTeacher searchCurrentTeachers(Integer start) {
        return requestWithAccessToken(requestFactory.get("services/users/search_current_teachers",
                Map.of("num", List.of("20"), "start", List.of(start.toString()))), UsosUserTeacher.class);
    }

    @Deprecated
    public UsosUserTeacher searchCurrentTeachers(String name) {
        return requestWithAccessToken(requestFactory.get("services/users/search_current_teachers", Map.of("name",
                List.of(name), "num", List.of("20"))), UsosUserTeacher.class);
    }

    @Deprecated
    public UsosUserTeacher searchCurrentTeachers(String name, Integer start) {
        return requestWithAccessToken(requestFactory.get("services/users/search_current_teachers", Map.of("name",
                List.of(name), "num", List.of("20"), "start", List.of(start.toString()))), UsosUserTeacher.class);
    }

    @Beta
    public String change(String altEmail) {
        return requestWithAccessToken(requestFactory.get("services/users/change", Map.of("alt_email",
                List.of(altEmail))));
    }

    @InProgress(description = "always return message: {message: Nonce already used}")
    public List<UsosUserEmploymentPosition> employmentPositions() {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/users/employment_positions", Map.of("fields",
                List.of("position", "faculty"))), UsosUserEmploymentPosition[].class));
    }

    public List<UsosUserEmploymentPosition> employmentPositions(String userId) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/users/employment_positions", Map.of("fields",
                List.of("position", "faculty"), "user_id", List.of(userId))), UsosUserEmploymentPosition[].class));
    }

    public UsosUserSearch search2(String language) {
        return requestWithAccessToken(requestFactory.get("services/users/search2", Map.of("fields",
                List.of("items[user[id|first_name|middle_names|last_name|previous_names|sex|titles|student_status|" +
                        "staff_status|email_access|email|email_url|has_email|homepage_url|profile_url|phone_numbers|" +
                        "mobile_numbers|office_hours|interests|has_photo|photo_urls|student_number|pesel|birth_date|" +
                        "revenue_office_id|citizenship|room|student_programmes|employment_functions|" +
                        "employment_positions|course_editions_conducted|postal_addresses|alt_email|" +
                        "external_ids|phd_student_status|library_card_id]|match]", "next_page"), "lang", List.of(language),
                "num", List.of("20"))), UsosUserSearch.class);
    }

    public UsosUserSearch search2(String language, Integer start) {
        return requestWithAccessToken(requestFactory.get("services/users/search2", Map.of("fields",
                List.of("items[user[id|first_name|middle_names|last_name|previous_names|sex|titles|student_status|" +
                        "staff_status|email_access|email|email_url|has_email|homepage_url|profile_url|phone_numbers|" +
                        "mobile_numbers|office_hours|interests|has_photo|photo_urls|student_number|pesel|birth_date|" +
                        "revenue_office_id|citizenship|room|student_programmes|employment_functions|" +
                        "employment_positions|course_editions_conducted|postal_addresses|alt_email|" +
                        "external_ids|phd_student_status|library_card_id]|match]", "next_page"), "lang", List.of(language),
                "num", List.of("20"), "start", List.of(start.toString()))), UsosUserSearch.class);
    }

    public UsosUserSearch search2(String language, String query) {
        return requestWithAccessToken(requestFactory.get("services/users/search2", Map.of("fields",
                        List.of("items[user[id|first_name|middle_names|last_name|previous_names|sex|titles|student_status|" +
                                "staff_status|email_access|email|email_url|has_email|homepage_url|profile_url|phone_numbers|" +
                                "mobile_numbers|office_hours|interests|has_photo|photo_urls|student_number|pesel|birth_date|" +
                                "revenue_office_id|citizenship|room|student_programmes|employment_functions|" +
                                "employment_positions|course_editions_conducted|postal_addresses|alt_email|" +
                                "external_ids|phd_student_status|library_card_id]|match]", "next_page"),
                        "lang", List.of(language), "query", List.of(query), "num", List.of("20"))),
                UsosUserSearch.class);
    }

    public UsosUserSearch search2(String language, String query, Integer start) {
        return requestWithAccessToken(requestFactory.get("services/users/search2", Map.of("fields",
                        List.of("items[user[id|first_name|middle_names|last_name|previous_names|sex|titles|student_status|" +
                                "staff_status|email_access|email|email_url|has_email|homepage_url|profile_url|phone_numbers|" +
                                "mobile_numbers|office_hours|interests|has_photo|photo_urls|student_number|pesel|birth_date|" +
                                "revenue_office_id|citizenship|room|student_programmes|employment_functions|" +
                                "employment_positions|course_editions_conducted|postal_addresses|alt_email|" +
                                "external_ids|phd_student_status|library_card_id]|match]", "next_page"),
                        "lang", List.of(language), "query", List.of(query), "num", List.of("20"), "start", List.of(start.toString()))),
                UsosUserSearch.class);
    }

    @Deprecated
    public List<UsosUserProgrammsSimple> studentProgrammes() {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/users/student_programmes"),
                UsosUserProgrammsSimple[].class));
    }

    public List<Map<Integer, UsosUser>> users(List<String> userIds) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/users/users", Map.of("fields",
                List.of("id", "first_name", "middle_names", "last_name", "previous_names", "sex", "titles",
                        "student_status", "staff_status", "email_access", "email", "email_url", "has_email", "homepage_url",
                        "profile_url", "phone_numbers", "mobile_numbers", "office_hours", "interests", "has_photo",
                        "photo_urls", "student_number", "pesel", "birth_date", "revenue_office_id", "citizenship",
                        "room", "student_programmes", "employment_functions", "employment_positions",
                        "course_editions_conducted", "postal_addresses", "alt_email", "external_ids",
                        "phd_student_status", "library_card_id"), "user_ids", userIds)), Map.class));
    }

    public String searchHistoryAffect(String userId) {
        return requestWithAccessToken(requestFactory.get("services/users/search_history_affect", Map.of("user_id",
                List.of(userId))));
    }

    @Deprecated
    public UsosUserSearchStudent searchStudents() {
        return requestWithAccessToken(requestFactory.get("services/users/search_students", Map.of("num",
                List.of("20"))), UsosUserSearchStudent.class);
    }

    @Deprecated
    public UsosUserSearchStudent searchStudents(Integer start) {
        return requestWithAccessToken(requestFactory.get("services/users/search_students", Map.of("num",
                List.of("20"), "start", List.of(start.toString()))), UsosUserSearchStudent.class);
    }

    @Deprecated
    public UsosUserSearchStudent searchStudents(String name) {
        return requestWithAccessToken(requestFactory.get("services/users/search_students", Map.of("name",
                List.of(name), "num", List.of("20"))), UsosUserSearchStudent.class);
    }

    @Deprecated
    public UsosUserSearchStudent searchStudents(String name, Integer start) {
        return requestWithAccessToken(requestFactory.get("services/users/search_students", Map.of("name",
                List.of(name), "num", List.of("20"), "start", List.of(start.toString()))), UsosUserSearchStudent.class);
    }

    public UsosUser user() {
        return requestWithAccessToken(requestFactory.get("services/users/user", Map.of("fields",
                List.of("id", "first_name", "middle_names", "last_name", "previous_names", "sex", "titles",
                        "student_status", "staff_status", "email_access", "email", "email_url", "has_email", "homepage_url",
                        "profile_url", "phone_numbers", "mobile_numbers", "office_hours", "interests", "has_photo",
                        "photo_urls", "student_number", "pesel", "birth_date", "revenue_office_id", "citizenship",
                        "room", "student_programmes", "employment_functions", "employment_positions",
                        "course_editions_conducted", "postal_addresses", "alt_email", "external_ids",
                        "phd_student_status", "library_card_id"))), UsosUser.class);
    }

    public UsosUser user(String userId) {
        return requestWithAccessToken(requestFactory.get("services/users/user", Map.of("fields",
                List.of("id", "first_name", "middle_names", "last_name", "previous_names", "sex", "titles",
                        "student_status", "staff_status", "email_access", "email", "email_url", "has_email", "homepage_url",
                        "profile_url", "phone_numbers", "mobile_numbers", "office_hours", "interests", "has_photo",
                        "photo_urls", "student_number", "pesel", "birth_date", "revenue_office_id", "citizenship",
                        "room", "student_programmes", "employment_functions", "employment_positions",
                        "course_editions_conducted", "postal_addresses", "alt_email", "external_ids",
                        "phd_student_status", "library_card_id"), "user_id", List.of(userId))), UsosUser.class);
    }

    public UsosUserPosition position(String id) {
        return requestWithAccessToken(requestFactory.get("services/users/position", Map.of("fields",
                List.of("id", "name", "employment_group"), "id", List.of(id))), UsosUserPosition.class);
    }

    @Deprecated
    public UsosUserSearchStudent searchCurrentStudents() {
        return requestWithAccessToken(requestFactory.get("services/users/search_current_students",
                Map.of("num", List.of("20"))), UsosUserSearchStudent.class);
    }

    @Deprecated
    public UsosUserSearchStudent searchCurrentStudents(Integer start) {
        return requestWithAccessToken(requestFactory.get("services/users/search_current_students",
                Map.of("num", List.of("20"), "start", List.of(start.toString()))), UsosUserSearchStudent.class);
    }

    @Deprecated
    public UsosUserSearchStudent searchCurrentStudents(String name) {
        return requestWithAccessToken(requestFactory.get("services/users/search_current_students", Map.of("name",
                List.of(name), "num", List.of("20"))), UsosUserSearchStudent.class);
    }

    @Deprecated
    public UsosUserSearchStudent searchCurrentStudents(String name, Integer start) {
        return requestWithAccessToken(requestFactory.get("services/users/search_current_students", Map.of("name",
                List.of(name), "num", List.of("20"), "start", List.of(start.toString()))), UsosUserSearchStudent.class);
    }

    public UsosUserUsersIndex staffIndex(List<String> facIds) {
        return requestWithAccessToken(requestFactory.get("services/users/staff_index", Map.of("fac_ids",
                facIds, "num", List.of("20"), "fields", List.of("users", "next_page", "total"))), UsosUserUsersIndex.class);
    }

    public UsosUserUsersIndex staffIndex(List<String> facIds, Integer start) {
        return requestWithAccessToken(requestFactory.get("services/users/staff_index", Map.of("fac_ids",
                facIds, "num", List.of("20"), "fields", List.of("users", "next_page", "total"), "start",
                List.of(start.toString()))), UsosUserUsersIndex.class);
    }

    public UsosUserUsersIndex studentIndex(List<String> programmeIds) {
        return requestWithAccessToken(requestFactory.get("services/users/student_index", Map.of("programme_ids",
                programmeIds, "num", List.of("20"), "fields", List.of("users[id|first_name|middle_names|last_name|" +
                        "previous_names|sex|titles|student_status|staff_status|email_access|email|email_url|has_email|" +
                        "homepage_url|profile_url|phone_numbers|mobile_numbers|office_hours|interests|has_photo|photo_urls|" +
                        "student_number|pesel|birth_date|revenue_office_id|citizenship|room|student_programmes|" +
                        "employment_functions|employment_positions|course_editions_conducted|postal_addresses|alt_email|" +
                        "external_ids|phd_student_status|library_card_id]", "next_page", "total"))), UsosUserUsersIndex.class);
    }

    public UsosUserUsersIndex studentIndex(List<String> programmeIds, Integer start) {
        return requestWithAccessToken(requestFactory.get("services/users/student_index", Map.of("programme_ids",
                programmeIds, "num", List.of("20"), "fields", List.of("users[id|first_name|middle_names|last_name|" +
                        "previous_names|sex|titles|student_status|staff_status|email_access|email|email_url|has_email|" +
                        "homepage_url|profile_url|phone_numbers|mobile_numbers|office_hours|interests|has_photo|photo_urls|" +
                        "student_number|pesel|birth_date|revenue_office_id|citizenship|room|student_programmes|" +
                        "employment_functions|employment_positions|course_editions_conducted|postal_addresses|alt_email|" +
                        "external_ids|phd_student_status|library_card_id]", "next_page", "total"), "start",
                List.of(start.toString()))), UsosUserUsersIndex.class);
    }

    public List<UsosUserEmploymentGroup> employmentGroupsIndex() {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/users/employment_groups_index", Map.of("fields",
                List.of("id", "name", "university_teachers"))), UsosUserEmploymentGroup[].class));
    }

    @Deprecated
    public UsosUserSearchStudent search() {
        return requestWithAccessToken(requestFactory.get("services/users/search", Map.of("num",
                List.of("20"))), UsosUserSearchStudent.class);
    }

    @Deprecated
    public UsosUserSearchStudent search(Integer start) {
        return requestWithAccessToken(requestFactory.get("services/users/search", Map.of("start",
                List.of(start.toString()), "num", List.of("20"))), UsosUserSearchStudent.class);
    }

    @Deprecated
    public UsosUserSearchStudent search(String name) {
        return requestWithAccessToken(requestFactory.get("services/users/search", Map.of("name",
                List.of(name), "num", List.of("20"))), UsosUserSearchStudent.class);
    }

    @Deprecated
    public UsosUserSearchStudent search(String name, Integer start) {
        return requestWithAccessToken(requestFactory.get("services/users/search", Map.of("name",
                        List.of(name), "num", List.of("20"), "start", List.of(start.toString()))),
                UsosUserSearchStudent.class);
    }

    @Deprecated
    public UsosUserSearchStudent searchStaff() {
        return requestWithAccessToken(requestFactory.get("services/users/search_staff", Map.of("num",
                List.of("20"))), UsosUserSearchStudent.class);
    }

    @Deprecated
    public UsosUserSearchStudent searchStaff(Integer start) {
        return requestWithAccessToken(requestFactory.get("services/users/search_staff", Map.of("start",
                List.of(start.toString()), "num", List.of("20"))), UsosUserSearchStudent.class);
    }

    @Deprecated
    public UsosUserSearchStudent searchStaff(String name) {
        return requestWithAccessToken(requestFactory.get("services/users/search_staff", Map.of("name",
                List.of(name), "num", List.of("20"))), UsosUserSearchStudent.class);
    }

    @Deprecated
    public UsosUserSearchStudent searchStaff(String name, Integer start) {
        return requestWithAccessToken(requestFactory.get("services/users/search_staff", Map.of("name",
                        List.of(name), "num", List.of("20"), "start", List.of(start.toString()))),
                UsosUserSearchStudent.class);
    }

}
