package com.sp.fc;

import com.sp.fc.paper.Paper;
import com.sp.fc.paper.PaperRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = AuthorityACLApplication.class)
class CacheTest {

    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private CacheManager cacheManager;

    Optional<Paper> getPaper(Long id) {
        return Optional.ofNullable(cacheManager.getCache("papers").get(id, Paper.class));
    }

    @Test
    void paperTest1() {
        Paper paper1 = Paper.builder().id(1L).title("paper1").build();
        paperRepository.save(paper1);

        assertEquals(Optional.empty(), getPaper(1L));
        paperRepository.findById(1L);

        assertTrue(getPaper(1L).isPresent());
    }
}
