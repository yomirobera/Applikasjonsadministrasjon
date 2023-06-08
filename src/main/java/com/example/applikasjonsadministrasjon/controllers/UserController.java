package com.example.applikasjonsadministrasjon.controllers;

import com.example.applikasjonsadministrasjon.mappers.StillingMapper;
import com.example.applikasjonsadministrasjon.mappers.UserMapper;
import com.example.applikasjonsadministrasjon.models.User;
import com.example.applikasjonsadministrasjon.models.dto.user.UserPostDTO;
import com.example.applikasjonsadministrasjon.models.dto.user.UserUpdateDTO;
import com.example.applikasjonsadministrasjon.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    private final StillingMapper stillingMapper;

    public UserController(UserService userService, UserMapper userMapper, StillingMapper stillingMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.stillingMapper = stillingMapper;
    }
    @GetMapping
    public ResponseEntity getAll() {

        return ResponseEntity.ok(userMapper.userToUserDto(userService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable String id) {
        return ResponseEntity.ok(userMapper.userToUserDto(userService.findById(id)));
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
    @GetMapping("{id}/getAllStilling")
    public ResponseEntity getAllProjects(@PathVariable String id) {
        return ResponseEntity.ok(stillingMapper.stillingToStillingDTO(userService.findAllStilling(id)));
    }


}
