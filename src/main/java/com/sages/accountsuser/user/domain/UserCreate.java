package com.sages.accountsuser.user.domain;

import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class UserCreate {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String role;

}
