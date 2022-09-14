package com.manish.dto.response;

import com.manish.enums.EmailType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class EmailResponse {
    private EmailType emailType;
    private String emailAddress;
}
