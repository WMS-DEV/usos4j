package dev.wms;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UsosNewsAPITest extends UsosAPIBaseTest {
    @Test
    void categoryIndex() {
        log(userApi.news().categoryIndex());
    }

    @Test
    void search() {
        log(userApi.news().search());
        log(userApi.news().search("2020-01-01"));
    }

    @Disabled
    @Test
    void article() {
        log(userApi.news().article("123"));
    }

    @Disabled
    @Test
    void articles() {
        log(userApi.news().articles(List.of("123", "456")));
    }
}
