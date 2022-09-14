package com.manish.mapper;

import com.manish.domain.embedded.Email;
import com.manish.domain.embedded.Name;
import com.manish.dto.request.EmailInput;
import com.manish.dto.request.NameInput;
import com.manish.dto.response.EmailResponse;
import com.manish.dto.response.NameResponse;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    Email fromEmailInputToEmail(EmailInput emailInput);
    EmailResponse fromEmailToEmailResponse(Email email);

    List<Email> fromEmailInputsToEmails(List<EmailInput> emailInputs);
    List<EmailResponse> fromEmailsToEmailResponses(Collection<Email> emails);
}
