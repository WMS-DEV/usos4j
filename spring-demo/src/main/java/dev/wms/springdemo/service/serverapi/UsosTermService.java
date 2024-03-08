package dev.wms.springdemo.service.serverapi;

import dev.wms.usos4j.client.UsosServerAPI;
import dev.wms.usos4j.model.terms.UsosTerm;
import dev.wms.usos4j.model.terms.UsosTermParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UsosTermService {

    private final UsosServerAPI usosServerApi;

    public UsosTerm getTerm(String termId) {
        return usosServerApi.terms().term(UsosTermParams.builder(termId != null ? termId : "2023/24-Z").build());
    }

}
