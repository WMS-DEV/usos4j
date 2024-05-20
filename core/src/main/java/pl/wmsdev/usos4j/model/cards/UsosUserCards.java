package pl.wmsdev.usos4j.model.cards;

public record UsosUserCards(String certificate, String systemKey, String cardKey, String structure,
                            String expirationDate, String dateOfWrite, String studentNumber, Boolean canBeWritten,
                            String card) {
}
