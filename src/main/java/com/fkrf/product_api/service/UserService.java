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
    private final BCryptPasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    public UserDTO getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return toDTO(user);
    }
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        if(userCreateDTO.getEmail().isEmpty() )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email can't be empty!");

        boolean emailExists = userRepository.findByEmail(userCreateDTO.getEmail()).isPresent();
        if (emailExists)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use!");
        User user = new User();
        user.setEmail(userCreateDTO.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setUserRole(userCreateDTO.getUserRole());
        user.setIsActive(userCreateDTO.getIsActive());
        User userSaved = userRepository.save(user);
        return toDTO(userSaved);
    }
    public UserDTO updateUser(UUID id, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if(userUpdateDTO.getEmail().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email can't be empty!");

        Optional<User> userExisting = userRepository.findByEmail(userUpdateDTO.getEmail());
        if(userExisting.isPresent() && !userExisting.get().getId().equals(id))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use!");

        user.setEmail(userUpdateDTO.getEmail());
        user.setIsActive(userUpdateDTO.getIsActive());
        User userUpdated = userRepository.save(user);

        return toDTO(userUpdated);
    }
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        userRepository.deleteById(id);
    }
}
