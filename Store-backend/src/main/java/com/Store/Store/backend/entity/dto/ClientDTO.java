package com.Store.Store.backend.entity.dto;

import com.Store.Store.backend.entity.emuns.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class ClientDTO {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String gender;
}
