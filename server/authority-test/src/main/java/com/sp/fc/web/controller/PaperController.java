package com.sp.fc.web.controller;

import com.sp.fc.web.service.Paper;
import com.sp.fc.web.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaperController {

    private final PaperService paperService;

//    @PreAuthorize("isStudent()")
    @PostFilter("notPrepareState(filterObject) && filterObject.studentIds.contains(#user.username)")
    @GetMapping("/mypapers")
    public List<Paper> myPapers(@AuthenticationPrincipal User user) {
        return paperService.getMyPapers(user.getUsername());
    }

    @PreAuthorize("hasPermission(#paperId, 'paper', 'read')")
    @GetMapping("/get/{paperId}")
    public Paper getPaper(@AuthenticationPrincipal User user, @PathVariable Long paperId) {
        return paperService.getPaper(paperId);
    }

}
