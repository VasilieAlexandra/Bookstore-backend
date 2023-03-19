package com.example.demo.controller;

import com.example.demo.model.dto.UserDto;
import com.example.demo.service.FirebaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final FirebaseService firebaseService;

    public AuthController(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @PostMapping("/register")
    //For testing purposes
    public ResponseEntity<UserDto> registerPerson(@Valid @RequestBody UserDto userDto) {
        // Checks if the personDto object is valid
        UserDto result = firebaseService.registerPerson(userDto);
        if (result == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

}
