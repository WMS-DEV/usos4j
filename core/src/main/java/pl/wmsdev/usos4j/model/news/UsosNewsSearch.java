package pl.wmsdev.usos4j.model.news;

import java.util.List;

public record UsosNewsSearch(List<UsosNewsArticle> items, Boolean nextPage, Integer total) {
}
