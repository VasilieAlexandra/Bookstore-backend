package com.example.demo.service.impl;


import com.example.demo.model.dto.UserDto;
import com.example.demo.service.FirebaseService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;


@Service
public class FirebaseServiceImpl implements FirebaseService {

    private final FirebaseApp firebaseApp;

    public FirebaseServiceImpl(FirebaseApp firebaseApp) {
        this.firebaseApp = firebaseApp;
    }

//    @Override
//    public UserDto registerPerson(UserDto userDto) {
//        return null;
//    }

    @Override
    public UserDto registerPerson(UserDto userDto){
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setPassword(userDto.getPassword())
                    .setEmail(userDto.getEmail());
            UserRecord result;
            try {
                result = FirebaseAuth.getInstance(firebaseApp).createUser(request);
            }
            catch(FirebaseAuthException e){return null;}
            //TODO log the exception
        return UserDto.builder()
                 .email(result.getEmail())
                 .build();
    }
    @Override
    public Boolean checkUserExists(String uid){

        try {
            FirebaseAuth.getInstance().getUser(uid);
            return true;
        } catch (FirebaseAuthException e) {

            throw new RuntimeException(e);
        }

    }
}
