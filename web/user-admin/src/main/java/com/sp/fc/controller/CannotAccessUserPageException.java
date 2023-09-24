package com.sp.fc.controller;

import org.springframework.security.access.AccessDeniedException;

public class CannotAccessUserPageException extends AccessDeniedException {

    public CannotAccessUserPageException() {
        super("유저 페이지 접근 거부");
    }

}
