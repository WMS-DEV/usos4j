package pl.wmsdev.usos4j.model.user;

import pl.wmsdev.usos4j.model.common.UsosCommonObject;
import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.courses.UsosCourseEditionConducted;

import java.time.LocalDate;
import java.util.Map;

public record UsosUser(Integer id, String lastName, String middleNames, String firstName, UsosUserPreviousNamesData[] previousNames,
                       String sex, UsosUserTitlesData titles, Integer studentStatus, Integer staffStatus,
                       String emailAccess, String email, String emailUrl, Boolean hasEmail, String homepageUrl,
                       String profileUrl, String[] phoneNumbers, String[] mobileNumbers,
                       UsosLocalizedString officeHours, UsosLocalizedString interests, Boolean hasPhoto,
                       Map<String, String> photoUrls, String studentNumber, String pesel, LocalDate birthDate,
                       String revenueOfficeId, UsosUserCitizenshipData citizenship, String room,
                       UsosStudentProgramme[] studentProgrammes, UsosUserEmploymentFunction[] employmentFunctions,
                       UsosUserEmploymentPosition[] employmentPositions, UsosCourseEditionConducted[] courseEditionsConducted,
                       UsosUserPostalAddressData[] postalAddresses, String altEmail, UsosUserExternalIdsData externalIds,
                       Integer phdStudentStatus, String libraryCardId) implements UsosCommonObject {
    public record UsosUserTitlesData(String before, String after) { }
    public record UsosUserExternalIdsData(String orcid, String pbnId) { }
    public record UsosUserOfficeHoursData(String orcid, String pbnId) { }
    public record UsosUserCitizenshipData(String id) { }
    public record UsosUserPreviousNamesData(String firstName, String lastName, LocalDate until) { }

    public record UsosUserPostalAddressData(String type, UsosLocalizedString typeName, String address) { }
}
