package pl.wmsdev.usos4j.api.plctests;

import pl.wmsdev.usos4j.model.plctests.UsosPlacementTest;
import pl.wmsdev.usos4j.model.plctests.UsosPlacementTestParams;

import java.util.List;

public interface UsosPlacementTestsServerAPI {

    UsosPlacementTest test(UsosPlacementTestParams params);
    List<String> testsIndex();

}
