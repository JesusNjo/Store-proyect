package com.Store.Store.backend.entity;

import com.Store.Store.backend.entity.emuns.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "client")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Client {

    @Id
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @Email
    @Indexed(unique = true)
    @NotBlank
    private String email;
    @Size(min = 8)
    @NotBlank
    private String password;
    private String address;
    private Gender gender;


}