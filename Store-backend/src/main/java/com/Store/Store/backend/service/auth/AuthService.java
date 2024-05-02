package com.Store.Store.backend.service.auth;

import com.Store.Store.backend.entity.Client;
import com.Store.Store.backend.entity.emuns.Gender;
import com.Store.Store.backend.entity.emuns.Role;
import com.Store.Store.backend.entity.login.ClientRequest;
import com.Store.Store.backend.entity.login.RegisterRequest;
import com.Store.Store.backend.entity.querys.AuthResponse;
import com.Store.Store.backend.repository.client.ClientRepository;
import com.Store.Store.backend.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://locahost:4200")
public class AuthService {

    private final ClientRepository clientRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(ClientRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails user=clientRepository.findByUsername(request.getUsername()).orElseThrow();
            String token=jwtService.getToken(user);
            System.out.println("User "+user.getUsername()+" is enter");
            return AuthResponse.builder()
                    .token(token)
                    .build();
        } catch (AuthenticationException e) {
            String errorMessage = "Error de autenticación: " + e.getMessage();
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        } catch (Exception e) {
            String errorMessage = "Error al procesar la solicitud: " + e.getMessage();
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }


    public AuthResponse register(RegisterRequest request){
         Client client = Client.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .gender(Gender.valueOf(request.getGender().toUpperCase()))
                .address(request.getAddress())
                .role(Role.USER)
                .build();

        clientRepository.findClientByEmail(client.getEmail()).ifPresentOrElse(present -> {
            log.error("This email is already exist " + client.getEmail());
        }, () -> {
            clientRepository.findByUsername(client.getUsername()).ifPresentOrElse(present -> {
                log.error("This username is already exist " + client.getUsername());

            }, () -> {
                log.info("Client was created successfully.. " + client.getName());
                clientRepository.save(client);
                log.info(client.toString(), "was created");
            });
        });


        return AuthResponse.builder()
                .token(jwtService.getToken(client))
                .build();
    }
}
