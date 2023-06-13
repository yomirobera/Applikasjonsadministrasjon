package com.example.applikasjonsadministrasjon.controllers;

import com.example.applikasjonsadministrasjon.mappers.StillingMapper;
import com.example.applikasjonsadministrasjon.models.Stilling;
import com.example.applikasjonsadministrasjon.models.dto.stilling.StillingPostDTO;
import com.example.applikasjonsadministrasjon.models.dto.stilling.StillingUpdateDTO;
import com.example.applikasjonsadministrasjon.services.stilling.StillingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/stilling")
public class StillingController {
    private final StillingService stillingService;
    private final StillingMapper stillingMapper;

    public StillingController(StillingService stillingService, StillingMapper stillingMapper) {
        this.stillingService = stillingService;
        this.stillingMapper = stillingMapper;
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

/*    @PostMapping("{id}/users/{uid}")
    public ResponseEntity addUser(@RequestBody StillingPostDTO stillingPostDTO, @PathVariable int id, @PathVariable String uid){
        Stilling stilling=stillingService.findById(id);
        //stilling.getUsers()
;

    }
*/


}
