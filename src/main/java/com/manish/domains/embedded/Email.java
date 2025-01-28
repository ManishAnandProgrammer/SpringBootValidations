package com.manish.domains.embedded;

import com.manish.enums.EmailType;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Email {
    private EmailType emailType;
    private String emailAddress;
}
