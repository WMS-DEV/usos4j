package pl.wmsdev.usos4j.model.apiref;

import pl.wmsdev.usos4j.model.common.UsosAccessor;

import java.util.List;

public record UsosApiRefMethodAuthOptions(UsosAccessor consumer, UsosAccessor token, Boolean administrativeOnly,
                                          Boolean sslRequired, List<String> scopes) {
}
