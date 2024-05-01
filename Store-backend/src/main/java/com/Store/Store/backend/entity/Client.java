package com.Store.Store.backend.entity;

import com.Store.Store.backend.entity.emuns.Gender;
import com.Store.Store.backend.entity.emuns.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document(collection = "client")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Client implements UserDetails {

    @Id
    private String id;
    @NotBlank
    private String username;
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
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
