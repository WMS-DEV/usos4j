package dev.wms.usos4j.api.plctests;

import dev.wms.usos4j.model.plctests.UsosPlacementTest;
import dev.wms.usos4j.model.plctests.UsosPlacementTestsParams;

import java.util.List;
import java.util.Map;

public interface UsosPlacementTestsServerAPI {

    UsosPlacementTest test(UsosPlacementTestsParams params);
    List<String> testsIndex();

}
