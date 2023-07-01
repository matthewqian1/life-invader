package com.example.lifeinvader.controllers;

import com.example.lifeinvader.model.CreateAccountForm;
import com.example.lifeinvader.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserAccountService userAccountService;
    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountForm form) {
        return ResponseEntity.ok(userAccountService.createAccount(form));
    }
}
