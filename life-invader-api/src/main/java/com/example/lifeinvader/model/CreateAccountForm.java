package com.example.lifeinvader.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAccountForm {
    String username;
    String password;
    String email;
    String profilePhoto;
}
