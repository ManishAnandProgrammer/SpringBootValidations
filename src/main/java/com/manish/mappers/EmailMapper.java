package com.manish.mappers;

import com.manish.domains.embedded.Email;
import com.manish.dtos.requests.EmailInput;
import com.manish.dtos.responses.EmailResponse;
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
