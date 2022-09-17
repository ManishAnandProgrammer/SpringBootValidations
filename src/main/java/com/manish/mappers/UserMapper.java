package com.manish.mappers;

import com.manish.domains.User;
import com.manish.dtos.requests.UserUpsertRequest;
import com.manish.dtos.responses.UserResponse;
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
