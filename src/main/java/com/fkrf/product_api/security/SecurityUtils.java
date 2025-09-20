package com.fkrf.product_api.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("securityUtils")
public class SecurityUtils {
    public boolean isSelf(Authentication authentication, UUID targetUserId) {
        if (authentication == null || targetUserId == null) {
            return false;
        }
        UUID loggedUserId = UUID.fromString(authentication.getName());
        return loggedUserId.equals(targetUserId);
    }
}
