package com.manish.service.command;

import com.manish.domain.User;
import com.manish.dto.request.UserUpsertRequest;
import com.manish.dto.response.UserResponse;
import com.manish.mapper.UserMapper;
import com.manish.repository.UserRepository;
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
