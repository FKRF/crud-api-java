package com.fkrf.product_api.dto;

import com.fkrf.product_api.enums.UserRole;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserCreateDTO {

    private String email;
    private String password;
    private UserRole userRole;
    private boolean isActive;
    public UserCreateDTO(String email, String password, UserRole userRole, boolean isActive) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.isActive = isActive;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UserRole getUserRole() {
        return userRole;
    }
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    public boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
