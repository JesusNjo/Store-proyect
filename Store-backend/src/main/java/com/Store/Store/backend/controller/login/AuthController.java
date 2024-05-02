package com.Store.Store.backend.controller.login;

import com.Store.Store.backend.entity.Client;
import com.Store.Store.backend.entity.login.ClientRequest;
import com.Store.Store.backend.entity.login.RegisterRequest;
import com.Store.Store.backend.entity.querys.AuthResponse;
import com.Store.Store.backend.exceptionHandler.ClientExistsException;
import com.Store.Store.backend.repository.client.ClientRepository;
import com.Store.Store.backend.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;
    private final ClientRepository clientRepository;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody ClientRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        Optional<Client> client = clientRepository.findClientByEmail(request.getEmail());
        if(client.isPresent()){
            throw new ClientExistsException("Client with email " + request.getEmail() + " already exists.");
        }
        return ResponseEntity.ok(authService.register(request));
    }

}
