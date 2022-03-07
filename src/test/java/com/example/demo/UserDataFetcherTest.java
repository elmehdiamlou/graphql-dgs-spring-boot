package com.example.demo;

import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {DgsAutoConfiguration.class, UserDataFetcher.class})
class UserDataFetcherTest {

    @Test
    void users() {
    }

    @Test
    void filterUserByUsername() {
    }

    @Test
    void getUserById() {
    }
}