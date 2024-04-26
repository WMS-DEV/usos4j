package pl.wmsdev.usos4j.api.progs;

import pl.wmsdev.usos4j.model.progs.*;

import java.util.Map;

public interface UsosProgsServerAPI {

	UsosProgramme programme(UsosProgrammeParam params);

	Map<String, UsosProgramme> programmes(UsosProgrammesParam params);

	UsosStage stage(UsosStageParam params);

	UsosProgrammeSearchResult search(UsosProgrammeSearchParam params);
}
