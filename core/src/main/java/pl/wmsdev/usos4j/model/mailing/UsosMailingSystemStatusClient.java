package pl.wmsdev.usos4j.model.mailing;

public record UsosMailingSystemStatusClient(Integer totalPendingMessageCount, Integer myPendingMessageCount,
                                            Boolean daemonRunning) {}