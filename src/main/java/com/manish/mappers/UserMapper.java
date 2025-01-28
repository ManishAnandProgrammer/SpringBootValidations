package com.manish.mappers;

import com.manish.domains.User;
import com.manish.dtos.requests.UserUpsertRequest;
import com.manish.dtos.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final EmailMapper emailMapper;
    private final NameMapper nameMapper;
    private final PhoneNumberMapper phoneNumberMapper;


    public User fromUpsertRequestToUser(UserUpsertRequest upsertRequest) {
        User user = new User();
        user.setId(upsertRequest.getId());
        user.setName(nameMapper.fromNameInputToName(upsertRequest.getName()));
        user.setEmails(emailMapper.fromEmailInputsToEmails(upsertRequest.getEmails()));
        user.setPhoneNumber(phoneNumberMapper.fromPhoneNumberInputToPhoneNumber(upsertRequest.getPhoneNumber()));
        return user;
    }

    public UserResponse fromUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(nameMapper.fromNameToNameResponse(user.getName()));
        userResponse.setEmails(emailMapper.fromEmailsToEmailResponses(user.getEmails()));
        userResponse.setPhoneNumber(phoneNumberMapper.fromPhoneNumberToPhoneNumberResponse(user.getPhoneNumber()));
        return userResponse;
    }

}
