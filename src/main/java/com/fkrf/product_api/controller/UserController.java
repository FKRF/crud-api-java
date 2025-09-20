package com.fkrf.product_api.controller;

import com.fkrf.product_api.dto.*;
import com.fkrf.product_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users3")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOS = userService.getAllUsers();
        return ResponseEntity.ok(userDTOS);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityUtils.isSelf(authentication, #id)")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreateDTO userDTO) {
        UserDTO userCreated = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityUtils.isSelf(authentication, #id)")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody UserUpdateDTO userUpdateDTO) {
        UserDTO userDTO = userService.updateUser(id, userUpdateDTO);
        return ResponseEntity.ok(userDTO);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> changeRole(@PathVariable UUID id, @RequestBody ChangeRoleDTO changeRoleDTO) {
        UserDTO updateUserRoleUser = userService.changeRole(id, changeRoleDTO.getUserRole());
        return ResponseEntity.ok(updateUserRoleUser);
    }
    @PutMapping("/{id}/password")
    @PreAuthorize("@securityUtils.isSelf(authentication, #id)")
    public ResponseEntity<Void> changePassword(@PathVariable UUID id, @RequestBody ChangePasswordDTO dto, Authentication authentication) {
        UUID callerId = UUID.fromString(authentication.getName());
        userService.changePassword(id, dto.getOldPassword(), dto.getNewPassword(), callerId);
        return ResponseEntity.noContent().build();
    }
}
