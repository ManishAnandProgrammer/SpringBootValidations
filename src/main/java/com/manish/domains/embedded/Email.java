package com.manish.domains.embedded;

import com.manish.enums.EmailType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Email {
    private EmailType emailType;
    private String emailAddress;
}
