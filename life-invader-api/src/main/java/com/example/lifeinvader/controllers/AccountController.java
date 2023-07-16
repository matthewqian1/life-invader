package com.example.lifeinvader.controllers;

import com.example.lifeinvader.model.Account;
import com.example.lifeinvader.model.CreateAccountForm;
import com.example.lifeinvader.model.LoginForm;
import com.example.lifeinvader.services.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Autowired
    private UserAccountService userAccountService;
    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountForm form) {
        System.out.println("HELLO");
        return ResponseEntity.ok(userAccountService.createAccount(form));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm form) {
        log.info("logging in with {}", form);
        try {
            return ResponseEntity.ok(userAccountService.login(form));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader (name="Authorization") String token) {
        userAccountService.logout(token);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/account")
    public ResponseEntity<Account> getAccount(@RequestHeader (name="Authorization") String token) {
        return ResponseEntity.ok(userAccountService.getAccountDetails(token));
    }

    @GetMapping("/ping")
    public ResponseEntity<String> logout() {
        System.out.println("HELLO");
        return ResponseEntity.ok().build();
    }
}
