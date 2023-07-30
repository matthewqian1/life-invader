package com.example.lifeinvader.repo;

import com.example.lifeinvader.model.UserSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepo extends MongoRepository<UserSession, String> {

}
