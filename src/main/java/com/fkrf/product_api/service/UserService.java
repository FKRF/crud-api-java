package com.fkrf.product_api.service;

import com.fkrf.product_api.dto.UserCreateDTO;
import com.fkrf.product_api.dto.UserDTO;
import com.fkrf.product_api.dto.UserUpdateDTO;
import com.fkrf.product_api.model.User;
import com.fkrf.product_api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getUserRole(),
                user.getIsActive(),
                user.getCreatedAt()
        );
    }
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users)
            userDTOS.add(toDTO(user));
        return userDTOS;
    }
    public Optional<UserDTO> getUserById(UUID id) {
        Optional<User> userOpt = userRepository.findById(id);
        if(!userOpt.isPresent())
            return Optional.empty();
        return Optional.of(toDTO(userOpt.get()));
    }
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        boolean emailExists = (userRepository.findByEmail(userCreateDTO.getEmail())).isPresent();
        if(userCreateDTO.getEmail().isEmpty())
            throw new RuntimeException("Email can't be empty!");
        if(emailExists)
            throw new RuntimeException("Email already in use!");
        User user = new User();
        user.setEmail(userCreateDTO.getEmail());
        user.setPasswordHash(hashPassword(userCreateDTO.getPassword()));
        user.setUserRole(userCreateDTO.getUserRole());
        user.setIsActive(userCreateDTO.getIsActive());
        User userSaved = userRepository.save(user);
        return toDTO(userSaved);
    }
    public Optional<UserDTO> updateUser(UUID id, UserUpdateDTO userUpdateDTO) {
        Optional<User> userOpt = userRepository.findById(id);
        if(!userOpt.isPresent())
            return Optional.empty();
        if(userUpdateDTO.getEmail().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email can't be empty!");
        Optional<User> userExisting = userRepository.findByEmail(userUpdateDTO.getEmail());
        if(userExisting.isPresent() && userExisting.get().getId() != userUpdateDTO.getId());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use!");
        User user = userOpt.get();
        user.setEmail(userUpdateDTO.getEmail());
        user.setIsActive(userUpdateDTO.getIsActive());
        User userUpdated = userRepository.save(user);
        return Optional.of(toDTO(userUpdated));
    }
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
    private String hashPassword(String textPassword) {
        return passwordEncoder.encode(textPassword);
    }
}
