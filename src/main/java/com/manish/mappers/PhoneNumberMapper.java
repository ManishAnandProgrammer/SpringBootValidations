package com.manish.mappers;

import com.manish.domains.embedded.PhoneNumber;
import com.manish.dtos.requests.PhoneNumberInput;
import com.manish.dtos.responses.PhoneNumberResponse;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberMapper {

    public PhoneNumber fromPhoneNumberInputToPhoneNumber(PhoneNumberInput phoneNumberInput) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setCountryDialCode(phoneNumberInput.getCountryDialCode());
        phoneNumber.setNumber(phoneNumberInput.getNumber());
        return phoneNumber;
    }

    public PhoneNumberResponse fromPhoneNumberToPhoneNumberResponse(PhoneNumber phoneNumber) {
        PhoneNumberResponse phoneNumberResponse = new PhoneNumberResponse();
        phoneNumberResponse.setCountryDialCode(phoneNumber.getCountryDialCode());
        phoneNumberResponse.setNumber(phoneNumber.getNumber());
        return phoneNumberResponse;
    }

}
