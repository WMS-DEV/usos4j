package pl.wmsdev.usos4j.model.mailing;

public record UsosMailingSystemStatusServer(Integer totalPendingMessageCount, Boolean daemonRunning) {
}
