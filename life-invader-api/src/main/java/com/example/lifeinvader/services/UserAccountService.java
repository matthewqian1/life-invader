package com.example.lifeinvader.services;

import com.example.lifeinvader.model.Account;
import com.example.lifeinvader.model.CreateAccountForm;
import com.example.lifeinvader.model.LoginForm;
import com.example.lifeinvader.repo.AccountRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manages user account and session actions
 */
@Service
public class UserAccountService {

    @Autowired
    AccountRepo accountRepo;

    /**
     * Creates a user account
     * @param form
     * @return
     */
    public String createAccount(CreateAccountForm form) {
        accountRepo.save(new Account(form.getUsername(), form.getPassword(), form.getEmail()));
        return null;
    }

    /**
     * verifies credentials and returns a session token for a successful login
     * @param form
     * @return
     */
    public String login(LoginForm form) {
        return null;
    }

    /**
     * logs the user out of the current session
     * @param sessionToken
     */
    public void logout(String sessionToken) {

    }


}
