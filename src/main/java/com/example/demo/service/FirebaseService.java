package com.example.demo.service;

import com.example.demo.model.dto.UserDto;

public interface FirebaseService {
    UserDto registerPerson(UserDto userDto);
    Boolean checkUserExists(String uid);
}
