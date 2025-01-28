package com.manish.mappers;

import com.manish.domains.embedded.Email;
import com.manish.dtos.requests.EmailInput;
import com.manish.dtos.responses.EmailResponse;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class EmailMapper {

    public Email fromEmailInputToEmail(EmailInput emailInput) {
        Email email = new Email();
        email.setEmailAddress(emailInput.getEmailAddress());
        email.setEmailType(emailInput.getEmailType());
        return email;
    }

    public EmailResponse fromEmailToEmailResponse(Email email) {
        EmailResponse emailResponse = new EmailResponse();
        emailResponse.setEmailAddress(email.getEmailAddress());
        emailResponse.setEmailType(email.getEmailType());
        return emailResponse;
    }

    public List<Email> fromEmailInputsToEmails(List<EmailInput> emailInputs) {
        return emailInputs.stream()
                .map(this::fromEmailInputToEmail)
                .toList();
    }

    public List<EmailResponse> fromEmailsToEmailResponses(Collection<Email> emails) {
        return emails.stream()
                .map(this::fromEmailToEmailResponse)
                .toList();
    }

}
