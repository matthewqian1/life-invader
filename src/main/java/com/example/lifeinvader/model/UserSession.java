package com.example.lifeinvader.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user_sessions")
@AllArgsConstructor
public class UserSession {
    @Id
    private String sessionId;
    private String username;
}
