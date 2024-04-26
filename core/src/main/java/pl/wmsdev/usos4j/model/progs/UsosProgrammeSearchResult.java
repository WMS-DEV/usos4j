package pl.wmsdev.usos4j.model.progs;

import pl.wmsdev.usos4j.model.common.UsosObject;

import java.util.List;

public record UsosProgrammeSearchResult(List<UsosProgrammeSearchResultItem> items, Boolean nextPage) implements UsosObject {
}
