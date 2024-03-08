package pl.wmsdev.usos4j.api.plctests;

import pl.wmsdev.usos4j.model.plctests.UsosPlacementTest;
import pl.wmsdev.usos4j.model.plctests.UsosPlacementTestsParams;

import java.util.List;

public interface UsosPlacementTestsServerAPI {

    UsosPlacementTest test(UsosPlacementTestsParams params);
    List<String> testsIndex();

}
