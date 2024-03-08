package pl.wmsdev.usos4j.api.news;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.docs.Beta;
import pl.wmsdev.usos4j.docs.NotTested;
import pl.wmsdev.usos4j.docs.NotTestedReason;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.news.UsosNewsArticle;
import pl.wmsdev.usos4j.model.news.UsosNewsCategoryIndex;
import pl.wmsdev.usos4j.model.news.UsosNewsSearch;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UsosNewsAPI extends UsosUserAPIDefinition {
    public UsosNewsAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    @NotTested(reason = NotTestedReason.NO_DATA)
    @Beta
    public UsosNewsArticle article(String articleId) {
        return requestWithAccessToken(requestFactory.get("services/news/article", Map.of("article_id", List.of(articleId),
                "fields", List.of("id", "author", "publication_date", "title", "headline_html", "content_html",
                        "expiry_date", "category", "faculty", "image_urls", "image_description", "event"))), UsosNewsArticle.class);
    }

    @NotTested(reason = NotTestedReason.NO_DATA)
    @Beta
    public List<UsosNewsArticle> articles(List<String> articleIds) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/news/articles", Map.of("article_ids",
                articleIds, "fields", List.of("id", "author", "publication_date", "title", "headline_html", "content_html",
                        "expiry_date", "category", "faculty", "image_urls", "image_description", "event"))), UsosNewsArticle[].class));
    }

    @Beta
    public List<UsosNewsCategoryIndex> categoryIndex() {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/news/category_index", Map.of("fields",
                List.of("id", "name"))), UsosNewsCategoryIndex[].class));
    }

    @NotTested(reason = NotTestedReason.NO_DATA)
    @Beta
    public UsosNewsSearch search() {
        return requestWithAccessToken(requestFactory.get("services/news/search", Map.of("fields",
                List.of("items", "next_page", "total"), "num", List.of("100"))), UsosNewsSearch.class);
    }

    @NotTested(reason = NotTestedReason.NO_DATA)
    @Beta
    public UsosNewsSearch search(String fromDate) {
        return requestWithAccessToken(requestFactory.get("services/news/search", Map.of("fields", List.of("items",
                "next_page", "total"), "num", List.of("100"), "from_date", List.of(fromDate))), UsosNewsSearch.class);
    }
}
