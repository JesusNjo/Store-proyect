package com.Store.Store.backend.controller.protect;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProtectedController {

    @PostMapping
    public String routerProtected(){

        return "Welcome to secure endpoint";
    }
}
