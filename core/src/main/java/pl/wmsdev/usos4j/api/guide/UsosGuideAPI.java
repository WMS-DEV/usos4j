package pl.wmsdev.usos4j.api.guide;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.docs.Beta;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.guide.*;

import java.util.List;
import java.util.Map;

public class UsosGuideAPI extends UsosUserAPIDefinition {

    public UsosGuideAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    @Beta
    public List<UsosGuideChapter> guide(UsosGuideGuideParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/guide/guide",
                params,
                FieldSelector.fromRequest(UsosGuideChapter.class)
        ), new TypeReference<>() {
        });
    }

    @Beta
    public UsosGuideChapter chapter(UsosGuideChapterParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/guide/chapter",
                params,
                FieldSelector.fromRequest(UsosGuideChapter.class)
        ), UsosGuideChapter.class);
    }

    @Beta
    public Map<String, UsosGuideChapter> chapters(UsosGuideChaptersParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/guide/chapters",
                params,
                FieldSelector.fromRequest(UsosGuideChapter.class)
        ), new TypeReference<>() {});
    }

    @Beta
    public UsosGuideEntry entry(UsosGuideEntryParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/guide/entry",
                params,
                FieldSelector.fromRequest(UsosGuideEntry.class)
        ), UsosGuideEntry.class);
    }

    @Beta
    public Map<String, UsosGuideEntry> entries(UsosGuideEntriesParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/guide/entries",
                params,
                FieldSelector.fromRequest(UsosGuideEntry.class)
        ), new TypeReference<>() {});
    }

    @Beta
    public UsosGuidePage page(UsosGuidePageParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/guide/page",
                params,
                FieldSelector.fromRequest(UsosGuidePage.class)

        ), UsosGuidePage.class);
    }

    @Beta
    public Map<String, UsosGuidePage> pages(UsosGuidePagesParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/guide/pages",
                params,
                FieldSelector.fromRequest(UsosGuidePage.class)
        ), new TypeReference<>() {
        });
    }
}
