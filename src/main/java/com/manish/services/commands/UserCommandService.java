package com.manish.services.commands;

import com.manish.domains.User;
import com.manish.dtos.requests.UserUpsertRequest;
import com.manish.dtos.responses.UserResponse;
import com.manish.mappers.UserMapper;
import com.manish.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCommandService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserCommandService(UserRepository userRepository,
                              UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse saveOrUpdate(final UserUpsertRequest upsertRequest) {
        User user = userMapper.fromUpsertRequestToUser(upsertRequest);
        User savedUser = userRepository.save(user);
        return userMapper.fromUserToUserResponse(savedUser);
    }
}
