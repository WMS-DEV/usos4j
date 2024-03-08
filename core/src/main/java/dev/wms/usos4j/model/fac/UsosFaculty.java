package dev.wms.usos4j.model.fac;

import dev.wms.usos4j.model.common.UsosLocalizedString;
import dev.wms.usos4j.model.common.UsosObject;

import java.util.List;

public record UsosFaculty(String id, UsosLocalizedString name, String profileUrl, String homepageUrl,
                          List<String> phoneNumbers, List<UsosFacultiesPhoneNumbers> phoneNumbers2,
                          String postalAddress, String email, Boolean isPublic, UsosFacultiesStats stats,
                          List<UsosFacultyShort> path, UsosFacultiesStaticMaps staticMapUrls, UsosFacultiesLogos logoUrls,
                          UsosFacultiesCovers coverUrls, String pdfFactsheetUrl) implements UsosObject {}