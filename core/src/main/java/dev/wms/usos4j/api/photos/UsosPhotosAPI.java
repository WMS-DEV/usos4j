package dev.wms.usos4j.api.photos;

import com.github.scribejava.core.oauth.OAuth10aService;
import dev.wms.usos4j.client.ScribeOAuthRequestFactory;
import dev.wms.usos4j.client.UsosUserAPIDefinition;
import dev.wms.usos4j.docs.Beta;
import dev.wms.usos4j.model.auth.UsosAccessToken;
import dev.wms.usos4j.model.photos.*;

import java.util.List;
import java.util.Map;

public class UsosPhotosAPI extends UsosUserAPIDefinition {
    public UsosPhotosAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    @Beta
    public UsosPhotosCanSeePhoto canSeePhoto() {
        return requestWithAccessToken(requestFactory.get("services/photos/can_see_photo", Map.of("fields",
                List.of("can_see_photo", "errors"))), UsosPhotosCanSeePhoto.class);
    }

    @Beta
    public UsosPhotosCanSeePhoto canSeePhoto(String userId) {
        return requestWithAccessToken(requestFactory.get("services/photos/can_see_photo", Map.of("fields",
                List.of("can_see_photo", "errors"), "user_id", List.of(userId))), UsosPhotosCanSeePhoto.class);
    }

    @Beta
    public String photo() {
        return requestWithAccessToken(requestFactory.get("services/photos/photo"));
    }

    @Beta
    public String photo(String userId) {
        return requestWithAccessToken(requestFactory.get("services/photos/photo", Map.of("user_id", List.of(userId))));
    }

    @Beta
    public String photo(Boolean blankPhoto) {
        return requestWithAccessToken(requestFactory.get("services/photos/photo", Map.of("blank_photo", List.of(String.valueOf(blankPhoto)))));
    }

    @Beta
    public String photo(String userId, Boolean blankPhoto) {
        return requestWithAccessToken(requestFactory.get("services/photos/photo", Map.of("blank_photo", List.of(String.valueOf(blankPhoto)),
                "user_id", List.of(userId))));
    }

    public UsosPhotosMyPhotoVisibility myPhotoVisibility() {
        return requestWithAccessToken(requestFactory.get("services/photos/my_photo_visibility", Map.of("fields",
                List.of("desc_for_user"))), UsosPhotosMyPhotoVisibility.class);
    }
}
