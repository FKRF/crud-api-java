package com.fkrf.product_api.controller;

import com.fkrf.product_api.dto.UserCreateDTO;
import com.fkrf.product_api.dto.UserDTO;
import com.fkrf.product_api.dto.UserUpdateDTO;
import com.fkrf.product_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOS = userService.getAllUsers();
        return ResponseEntity.ok(userDTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreateDTO userDTO) {
        UserDTO userCreated = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody UserUpdateDTO userUpdateDTO) {
        UserDTO userDTO = userService.updateUser(id, userUpdateDTO);
        return ResponseEntity.ok(userDTO);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
