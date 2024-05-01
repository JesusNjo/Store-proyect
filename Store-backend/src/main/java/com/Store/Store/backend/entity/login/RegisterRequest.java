package com.Store.Store.backend.entity.login;

import com.Store.Store.backend.entity.emuns.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String address;
    private String gender;
    private String role;
}
