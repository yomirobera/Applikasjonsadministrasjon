package com.example.applikasjonsadministrasjon.controllers;

import com.example.applikasjonsadministrasjon.mappers.StillingMapper;
import com.example.applikasjonsadministrasjon.models.Stilling;
import com.example.applikasjonsadministrasjon.models.User;
import com.example.applikasjonsadministrasjon.models.dto.stilling.StillingDTO;
import com.example.applikasjonsadministrasjon.models.dto.stilling.StillingPostDTO;
import com.example.applikasjonsadministrasjon.models.dto.stilling.StillingUpdateDTO;
import com.example.applikasjonsadministrasjon.services.stilling.StillingService;
import com.example.applikasjonsadministrasjon.services.user.UserService;
import org.aspectj.lang.annotation.After;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/stilling")
public class StillingController {
    private final StillingService stillingService;
    private final StillingMapper stillingMapper;
    private final UserService userService;

    public StillingController(StillingService stillingService, StillingMapper stillingMapper, UserService userService) {
        this.stillingService = stillingService;
        this.stillingMapper = stillingMapper;
        this.userService= userService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(stillingMapper.stillingToStillingDTO(
                stillingService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {
        return ResponseEntity.ok(stillingMapper.stillingToStillingDTO(stillingService.findById(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody StillingUpdateDTO stillingDTO, @PathVariable int id) {
        // Validates if body is correct
        if (id != stillingDTO.getId())
            return ResponseEntity.badRequest().build();

        stillingService.update(stillingMapper.stillingUpdateDtoToStilling(stillingDTO));
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity add(@RequestBody StillingPostDTO stillingDto) {
        Stilling stilling = stillingService.add(stillingMapper.stillingPostDtoToStilling(stillingDto));
        URI location = URI.create("stilling/" + stilling.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        stillingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

   @PutMapping("{id}/users/{uid}")
   public ResponseEntity addUser(@PathVariable int id, @PathVariable String uid) {
       Stilling stilling = stillingService.findById(id);
       User user =userService.findById(uid);

       if (stilling == null || user == null) {
           // Handle invalid stilling or user
           return ResponseEntity.badRequest().build();
       }

       // Add the user to the stilling
       stilling.getUsers().add(user);
       //stillingService.add(stilling);
       // Update the stilling
       stillingService.update(stilling);

       // Update the user's stilling
       //userService.add(user);
       user.getStilling().add(stilling);
       userService.update(user);

       return ResponseEntity.noContent().build();
   }

    @GetMapping("{id}/users")
    public ResponseEntity getUsersByStillingId(@PathVariable int id) {
        StillingDTO stilling=stillingMapper.stillingToStillingDTO(stillingService.findById(id));
        Set<String> users=stilling.getUsers();


        return ResponseEntity.ok(users);
    }







}
