package com.fkrf.product_api.dto;

import com.fkrf.product_api.enums.UserRole;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String email;
    private UserRole userRole;
    private boolean isActive;
    private LocalDateTime createdAt;
    public UserDTO() {}
    public UserDTO(UUID id, String email, UserRole userRole, boolean isActive, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.userRole = userRole;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }
    public UUID getId() {
        return id;
    }
    public UserRole getUserRole() {
        return userRole;
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
