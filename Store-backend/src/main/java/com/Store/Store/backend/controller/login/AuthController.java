package com.Store.Store.backend.controller.login;

import com.Store.Store.backend.entity.login.ClientRequest;
import com.Store.Store.backend.entity.login.RegisterRequest;
import com.Store.Store.backend.entity.querys.AuthResponse;
import com.Store.Store.backend.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody ClientRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
}
