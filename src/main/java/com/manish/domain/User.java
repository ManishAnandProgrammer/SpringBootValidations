package com.manish.domain;

import com.manish.domain.embedded.Email;
import com.manish.domain.embedded.Name;
import com.manish.domain.embedded.PhoneNumber;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private List<Email> emails;
}
