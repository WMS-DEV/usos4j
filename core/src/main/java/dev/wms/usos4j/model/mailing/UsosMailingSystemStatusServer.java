package dev.wms.usos4j.model.mailing;

public record UsosMailingSystemStatusServer(int totalPendingMessageCount, boolean daemonRunning) {
}
