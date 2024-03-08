package pl.wmsdev.usos4j.model.apisrv;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record UsosApiSrvConsumer(String name, String url, String email,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dateRegistered,
                                 List<String> administrativeMethods, List<String> tokenScopes) {
}
