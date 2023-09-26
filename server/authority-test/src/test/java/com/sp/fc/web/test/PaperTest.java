package com.sp.fc.web.test;

import com.sp.fc.web.service.Paper;
import com.sp.fc.web.service.PaperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaperTest extends WebIntegrationTest {

    @Autowired
    private PaperService paperService;

    TestRestTemplate client = new TestRestTemplate();

    private Paper paper1 = Paper.builder()
            .paperId(1L).title("시험 1").tutorId("tutor1").studentIds(List.of("user1")).state(Paper.State.PREPARE)
            .build();
    private Paper paper2 = Paper.builder()
            .paperId(2L).title("시험 2").tutorId("tutor1").studentIds(List.of("user2")).state(Paper.State.PREPARE)
            .build();

    @Test
    void getPaperList() {
        // given
        paperService.setPaper(paper1);
        client = new TestRestTemplate("user1", "1111");

        // when
        ResponseEntity<List<Paper>> response = client.exchange(uri("/paper/mypapers"),
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        // then
        assertEquals(200, response.getStatusCodeValue());
    }
}
