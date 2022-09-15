package com.manish.mapper;

import com.manish.domain.User;
import com.manish.dto.request.UserUpsertRequest;
import com.manish.dto.response.UserResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    uses = {EmailMapper.class, NameMapper.class, PhoneNumberMapper.class}
)
public interface UserMapper {
    User fromUpsertRequestToUser(UserUpsertRequest upsertRequest);
    UserResponse fromUserToUserResponse(User user);
}
