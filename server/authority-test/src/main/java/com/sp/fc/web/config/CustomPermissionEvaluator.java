package com.sp.fc.web.config;

import com.sp.fc.web.service.Paper;
import com.sp.fc.web.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@RequiredArgsConstructor
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Lazy
    private final PaperService paperService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        Paper paper = paperService.getPaper((long) targetId);

        if (paper == null) throw new AccessDeniedException("시험지가 존재하지 않습니다.");
        if (paper.getState() == Paper.State.PREPARE) return false;

        return paper.getStudentIds().stream()
                .anyMatch(userId -> userId.equals(authentication.getName()));
    }
}
