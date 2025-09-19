package com.fkrf.product_api.dto;

import com.fkrf.product_api.enums.UserRole;

import java.util.UUID;

public class UserUpdateDTO {
    private UUID id;
    private String email;
    private boolean isActive;
    public UserUpdateDTO(UUID id, String email, boolean isActive) {
        this.id = id;
        this.email = email;
        this.isActive = isActive;
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
    public boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
