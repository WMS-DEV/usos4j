package pl.wmsdev.springdemo.service.serverapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wmsdev.usos4j.client.UsosServerAPI;
import pl.wmsdev.usos4j.model.terms.UsosTerm;
import pl.wmsdev.usos4j.model.terms.UsosTermParams;

@RequiredArgsConstructor
@Component
public class UsosTermService {

    private final UsosServerAPI usosServerApi;

    public UsosTerm getTerm(String termId) {
        return usosServerApi.terms().term(UsosTermParams.builder(termId != null ? termId : "2023/24-Z").build());
    }

}
