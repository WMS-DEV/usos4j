package pl.wmsdev.usos4j.model.mailing;

public record UsosMailingSystemStatusClient(int totalPendingMessageCount, int myPendingMessageCount, boolean daemonRunning) {}