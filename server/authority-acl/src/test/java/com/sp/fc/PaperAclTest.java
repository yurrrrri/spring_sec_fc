package com.sp.fc;

import com.sp.fc.paper.Paper;
import com.sp.fc.paper.PaperRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = AuthorityACLApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaperAclTest {
    
    @LocalServerPort
    private int port;
    @Autowired
    private PaperRepository paperRepository;
    
    public String url(long paperId) {
        return "http://localhost:" + port + "/paper" + paperId;
    }

    @BeforeEach
    void before() {
        paperRepository.deleteAll();

        Paper paper1 = new Paper(1L, "paper1", "tutor1", Paper.State.PREPARE);
        paperRepository.save(paper1);
    }

    @Test
    void test1() {
        TestRestTemplate client = new TestRestTemplate("student1", "1111");
        ResponseEntity<Paper> resp = client.getForEntity(url(1L), Paper.class);

        System.out.println(resp.getBody());
    }
}
