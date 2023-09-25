package com.sp.fc.web.controller;

import com.sp.fc.web.service.SecurityMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final SecurityMessageService securityMessageService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/greeting")
    public String greeting() {
        return "Hello";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/greeting/{name}")
    public String greeting(@PathVariable String name) {
        return "Hello " + securityMessageService.message(name);
    }

}
