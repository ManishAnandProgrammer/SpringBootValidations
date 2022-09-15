package com.manish.mapper;

import com.manish.domain.embedded.Name;
import com.manish.domain.embedded.PhoneNumber;
import com.manish.dto.request.NameInput;
import com.manish.dto.request.PhoneNumberInput;
import com.manish.dto.response.NameResponse;
import com.manish.dto.response.PhoneNumberResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneNumberMapper {
    PhoneNumber fromPhoneNumberInputToPhoneNumber(PhoneNumberInput phoneNumberInput);
    PhoneNumberResponse fromPhoneNumberToPhoneNumberResponse(PhoneNumber phoneNumber);
}
