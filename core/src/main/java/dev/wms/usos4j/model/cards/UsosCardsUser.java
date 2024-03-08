package dev.wms.usos4j.model.cards;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record UsosCardsUser(String id, UsosCardsUserData user, String contactChipUid, String contactlessChipUid,
                            String barcodeNumber,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate expirationDate,
                            String type,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate dateOfIssue) {
}
