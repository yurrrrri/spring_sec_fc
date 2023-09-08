package com.sp.fc.web;

import com.sp.fc.web.student.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MultiChainProxyTest {

    @LocalServerPort
    int port;

    TestRestTemplate testClient = new TestRestTemplate("choi", "1");

    @DisplayName("1. choi:1로 로그인해서 학생 리스트를 내려받는다.")
    @Test
    void t001() {
        ResponseEntity<List<Student>> resp = testClient.exchange("http://localhost:" + port + "/api/teacher/students",
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().size()).isEqualTo(3);
    }
}
