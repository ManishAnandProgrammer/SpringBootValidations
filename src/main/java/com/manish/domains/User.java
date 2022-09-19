package com.manish.domains;

import com.manish.domains.embedded.Email;
import com.manish.domains.embedded.Name;
import com.manish.domains.embedded.PhoneNumber;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private PhoneNumber phoneNumber;

    @ElementCollection
    private List<Email> emails;

    private String password;
}
