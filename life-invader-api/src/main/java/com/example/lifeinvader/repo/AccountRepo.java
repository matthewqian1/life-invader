package com.example.lifeinvader.repo;

import com.example.lifeinvader.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepo extends MongoRepository<Account, String> {
}
