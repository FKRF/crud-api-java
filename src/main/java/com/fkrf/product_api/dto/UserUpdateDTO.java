package com.fkrf.product_api.dto;

import com.fkrf.product_api.enums.UserRole;

import java.util.UUID;

public class UserUpdateDTO {
    private UUID id;
    private String email;
    public UserUpdateDTO(UUID id, String email) {
        this.id = id;
        this.email = email;
    }
    public UUID getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
