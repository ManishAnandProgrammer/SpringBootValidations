package com.manish.service.query;

import com.manish.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService {
    private final UserRepository userRepository;

    public UserQueryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
