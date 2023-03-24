package com.example.demo.service.impl;


import com.example.demo.service.FirebaseService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.stereotype.Service;


@Service
public class FirebaseServiceImpl implements FirebaseService {

    private final FirebaseApp firebaseApp;

    public FirebaseServiceImpl(FirebaseApp firebaseApp) {
        this.firebaseApp = firebaseApp;
    }


    @Override
    public Boolean checkUserExists(String uid) {

        try {
            FirebaseAuth.getInstance().getUser(uid);
            return true;
        } catch (FirebaseAuthException e) {

            throw new RuntimeException(e);
        }

    }
}
