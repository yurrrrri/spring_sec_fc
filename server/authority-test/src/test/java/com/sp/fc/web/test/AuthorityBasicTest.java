package com.sp.fc.web.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorityBasicTest extends WebIntegrationTest {

    TestRestTemplate client;

    @Test
    void greetingTest() {
        client = new TestRestTemplate("user1", "1111");
        ResponseEntity<String> response = client.getForEntity(uri("/greeting"), String.class);

        assertEquals("Hello", response.getBody());
    }

    @Test
    void greetingWithNameTest() {
        client = new TestRestTemplate("user1", "1111");
        ResponseEntity<String> response = client.getForEntity(uri("/greeting/yuri"), String.class);

        assertEquals("Hello yuri", response.getBody());
    }
}
