package pl.wmsdev.usos4j.model.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UsosScope {

    ADM_DOCUMENTS("adm_documents", false, UsosScopeCategory.YELLOW),
    CARDS("cards", false, UsosScopeCategory.GREEN),
    CHANGE_ALL_PREFERENCES("change_all_preferences", false, UsosScopeCategory.RED),
    CRSTESTS("crstests", false, UsosScopeCategory.GREEN),
    DORM_ADMIN("dorm_admin", false, UsosScopeCategory.RED),
    EDIT_USER_ATTRS("edit_user_attrs", false, UsosScopeCategory.RED),
    EMAIL("email", false, UsosScopeCategory.GREEN),
    EVENTS("events", false, UsosScopeCategory.YELLOW),
    GRADES("grades", false, UsosScopeCategory.YELLOW),
    GRADES_WRITE("grades_write", false, UsosScopeCategory.RED),
    MAILCLIENT("mailclient", false, UsosScopeCategory.YELLOW),
    MOBILE_NUMBERS("mobile_numbers", false, UsosScopeCategory.YELLOW),
    OFFLINE_ACCESS("offline_access", false, UsosScopeCategory.YELLOW),
    OTHER_EMAILS("other_emails", false, UsosScopeCategory.YELLOW),
    PAYMENTS("payments", false, UsosScopeCategory.RED),
    PERSONAL("personal", false, UsosScopeCategory.RED),
    PHOTO("photo", false, UsosScopeCategory.GREEN),
    PLACEMENT_TESTS("placement_tests", false, UsosScopeCategory.GREEN),
    SESSION_DEBUGGING_PERMS("session_debugging_perms", true, UsosScopeCategory.RED),
    SLIPS("slips", false, UsosScopeCategory.YELLOW),
    SLIPS_ADMIN("slips_admin", false, UsosScopeCategory.RED),
    STAFF_PERSPECTIVE("staff_perspective", false, UsosScopeCategory.YELLOW),
    STUDENT_EXAMS("student_exams", false, UsosScopeCategory.GREEN),
    STUDENT_EXAMS_WRITE("student_exams_write", false, UsosScopeCategory.RED),
    STUDIES("studies", false, UsosScopeCategory.GREEN),
    SURVEYS_FILLING("surveys_filling", false, UsosScopeCategory.YELLOW),
    SURVEYS_REPORTS("surveys_reports", false, UsosScopeCategory.YELLOW),
    THESES_PROTOCOLS_WRITE("theses_protocols_write", false, UsosScopeCategory.RED);

    @Getter
    private final String scope;
    @Getter
    private final boolean adminOnly;

    @Getter
    private final UsosScopeCategory category;

}
