package com.manish.services.queries;

import com.manish.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService {
    private final UserRepository userRepository;

    public UserQueryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
