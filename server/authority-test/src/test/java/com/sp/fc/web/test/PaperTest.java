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
    private Paper paper3 = Paper.builder()
            .paperId(3L).title("시험 3").tutorId("tutor1").studentIds(List.of("user1")).state(Paper.State.READY)
            .build();

    @Test
    void getPaperList() {
        // given
        paperService.setPaper(paper1);
        paperService.setPaper(paper2);
        paperService.setPaper(paper3);

        client = new TestRestTemplate("user1", "1111");

        // when
        ResponseEntity<List<Paper>> response = client.exchange(uri("/paper/mypapers"),
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        // then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void cannotAccessTest() {
        // given
        paperService.setPaper(paper2);
        client = new TestRestTemplate("user1", "1111");

        // when
        ResponseEntity<Object> response = client.exchange(uri("/paper/get/2"),
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        // then
        assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    void cannotAccessPreparePaperTest() {
        // given
        paperService.setPaper(paper2);
        client = new TestRestTemplate("user2", "1111");

        // when
        ResponseEntity<Object> response = client.exchange(uri("/paper/get/2"),
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        // then
        assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    void takeAllPages() {
        paperService.setPaper(paper1);
        paperService.setPaper(paper2);
        paperService.setPaper(paper3);

        client = new TestRestTemplate("primary", "1111");
        ResponseEntity<List<Paper>> response = client.exchange(uri("/paper/getPapersByPrimary"),
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(3, response.getBody().size());
    }
}
