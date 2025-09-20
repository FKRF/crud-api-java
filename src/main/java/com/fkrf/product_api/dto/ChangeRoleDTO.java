package com.fkrf.product_api.dto;

import com.fkrf.product_api.enums.UserRole;

public class ChangeRoleDTO {
    private UserRole userRole;
    public UserRole getUserRole() {
        return userRole;
    }
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
