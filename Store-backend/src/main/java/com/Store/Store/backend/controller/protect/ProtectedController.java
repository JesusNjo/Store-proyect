package com.Store.Store.backend.controller.protect;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProtectedController {

    @PostMapping
    public String routerProtected(){

        return "Welcome to secure endpoint";
    }
}
