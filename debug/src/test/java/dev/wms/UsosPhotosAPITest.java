package dev.wms;

import org.junit.jupiter.api.Test;

public class UsosPhotosAPITest extends UsosAPIBaseTest {
    @Test
    void myPhotoVisibility() {
        log(userApi.photos().myPhotoVisibility());
    }

    @Test
    void canSeePhoto() {
        log(userApi.photos().canSeePhoto());
        log(userApi.photos().canSeePhoto("570"));
    }

    @Test
    void photo() {
        log(userApi.photos().photo());
        log(userApi.photos().photo("570"));
        log(userApi.photos().photo(true));
        log(userApi.photos().photo("563223", true));
    }

}
