package com.example.lifeinvader.services;

import com.example.lifeinvader.config.TestUtils;
import com.example.lifeinvader.model.Account;
import com.example.lifeinvader.model.CreateAccountForm;
import com.example.lifeinvader.model.LoginForm;
import com.example.lifeinvader.model.UserSession;
import com.example.lifeinvader.repo.AccountRepo;
import com.example.lifeinvader.repo.SessionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Manages user account and session actions
 */
@Slf4j
@Service
public class UserAccountService {

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private SessionRepo sessionRepo;
    @Autowired
    private TestUtils testUtils;

    public UserAccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    /**
     * Creates a user account
     * @param form
     * @return
     */
    public String createAccount(CreateAccountForm form) {
        accountRepo.save(new Account(form.getUsername(), form.getPassword(), form.getEmail(), form.getProfilePhoto(),form.getAge(), form.getWeightKg(), form.getHeightCm(), form.getActivityLevel(), form.getDailyCalorieGoal()));
        testUtils.loadTestDetails(form.getUsername());
        return null;
    }

    /**
     * verifies credentials and returns a session token for a successful login
     * @param form
     * @return
     */
    public String login(LoginForm form) throws Exception {
        Optional<Account> optional = accountRepo.findById(form.getUsername());
        if (optional.isEmpty()) {
            throw new Exception("username is invalid");
        }

        if (!optional.get().getPassword().equals(form.getPassword())) {
            throw new Exception("password for the username is incorrect");
        }

        String sessionToken = generateSessionToken();
        sessionRepo.save( new UserSession(sessionToken, form.getUsername()));
        return sessionToken;
    }

    /**
     * logs the user out of the current session
     * @param sessionToken
     */
    public void logout(String sessionToken) {
        sessionRepo.deleteById(sessionToken);
    }

    public Account getAccountDetails (String sessionToken) {
        log.info("fetching account details for session id {}", sessionToken);
        return accountRepo.findById(
                sessionRepo.findById(sessionToken)
                        .get()
                        .getUsername()
        )
                .get();
    }

    private String generateSessionToken() {
        return UUID.randomUUID().toString();
    }

}
