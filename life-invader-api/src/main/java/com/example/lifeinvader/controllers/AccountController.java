package com.example.lifeinvader.controllers;

import com.example.lifeinvader.model.CreateAccountForm;
import com.example.lifeinvader.model.LoginForm;
import com.example.lifeinvader.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserAccountService userAccountService;
    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountForm form) {
        return ResponseEntity.ok(userAccountService.createAccount(form));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm form) throws Exception {
        return ResponseEntity.ok(userAccountService.login(form));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader (name="Authorization") String token) {
        userAccountService.logout(token);
        return ResponseEntity.ok().build();
    }
}
