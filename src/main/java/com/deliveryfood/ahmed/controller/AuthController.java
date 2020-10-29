package com.deliveryfood.ahmed.controller;


import com.deliveryfood.ahmed.dto.PersonDto;
import com.deliveryfood.ahmed.service.PersonService;
import com.deliveryfood.ahmed.utils.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    public final PersonService personService;

    public AuthController(PersonService personService) {
        this.personService = personService;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> createPerson(@RequestBody PersonDto personDto) throws URISyntaxException {
        if (personService.existsByEmail(personDto.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email: "+ personDto.getEmail()+" already exists, please try another or sign in"));
        }
        if (personService.existsByUsername(personDto.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Username: "+ personDto.getUsername()+" already exists, please try another or sign in"));
        }
        return ResponseEntity.created(new URI("/api/v1/person/create")).body(personService.createPerson(personDto));
    }
}
