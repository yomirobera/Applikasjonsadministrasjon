package com.example.applikasjonsadministrasjon.controllers;

import com.example.applikasjonsadministrasjon.mappers.UserMapper;
import com.example.applikasjonsadministrasjon.models.User;
import com.example.applikasjonsadministrasjon.models.dto.user.UserPostDTO;
import com.example.applikasjonsadministrasjon.models.dto.user.UserUpdateDTO;
import com.example.applikasjonsadministrasjon.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;


    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(userMapper.userToUserDto(userService.findAll()));
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody UserUpdateDTO userDTO, @PathVariable String id) {
        // Validates if body is correct
        if (!id.equals(userDTO.getId()))
            return ResponseEntity.badRequest().build();

        userService.update(userMapper.userUpdateDtoToUser(userDTO));
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity add(@RequestBody UserPostDTO userDto) {
        User user = userService.add(userMapper.userPostDtoToUser(userDto));
        URI location = URI.create("users/" + user.getId());
        return ResponseEntity.created(location).build();
    }
}
