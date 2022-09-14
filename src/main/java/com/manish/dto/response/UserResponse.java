package com.manish.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponse {
    private Long id;
    private NameResponse name;
    private PhoneNumberResponse phoneNumber;
    private List<EmailResponse> emails;
}
