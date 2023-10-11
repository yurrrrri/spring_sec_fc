package com.sp.fc.paper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PaperService {

    private final PaperRepository paperRepository;

    public void setPaper(Paper paper) {
        paperRepository.save(paper);
    }

    public Optional<Paper> getPaper(Long paperId) {
        return paperRepository.findById(paperId);
    }
}
