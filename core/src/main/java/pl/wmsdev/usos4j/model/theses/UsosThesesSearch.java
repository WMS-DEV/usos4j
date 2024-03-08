package pl.wmsdev.usos4j.model.theses;

import pl.wmsdev.usos4j.model.common.UsosCommonObject;
import pl.wmsdev.usos4j.model.common.UsosObject;

import java.util.List;

public record UsosThesesSearch(List<UsosThesesSearchItem> items, boolean nextPage) implements UsosObject {

    public record UsosThesesSearchItem(UsosThesis thesis, String match) implements UsosCommonObject {}
}
