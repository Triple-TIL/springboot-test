package com.example.springtest.exam.integration;

import com.example.springtest.exam.application.RedisService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisServiceTest extends IntegrationTest {

    @Autowired
    private RedisService redisService;

    @Test
    @DisplayName("Redis Get / Set 테스트")
    public void redisGetSetTests() {
        String expectValue = "hello";
        String key = "hi";

        redisService.set(key, expectValue);

        String actualValue = redisService.get(key);

        Assertions.assertEquals(expectValue, actualValue);
    }

}
