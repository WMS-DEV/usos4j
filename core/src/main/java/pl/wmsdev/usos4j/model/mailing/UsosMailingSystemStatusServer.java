package pl.wmsdev.usos4j.model.mailing;

public record UsosMailingSystemStatusServer(int totalPendingMessageCount, boolean daemonRunning) {
}
