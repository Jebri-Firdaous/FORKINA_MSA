package com.example.serviceprojecthamzambarki.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer idUser;  // Match the field name from user-management
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}