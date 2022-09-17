package com.manish.mappers;

import com.manish.domains.embedded.PhoneNumber;
import com.manish.dtos.requests.PhoneNumberInput;
import com.manish.dtos.responses.PhoneNumberResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneNumberMapper {
    PhoneNumber fromPhoneNumberInputToPhoneNumber(PhoneNumberInput phoneNumberInput);
    PhoneNumberResponse fromPhoneNumberToPhoneNumberResponse(PhoneNumber phoneNumber);
}
