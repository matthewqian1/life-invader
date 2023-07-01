package com.example.lifeinvader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user_accounts")
@Data
@AllArgsConstructor
public class Account {
    @Id
    private String username;
    private String password;
    private String email;
}
