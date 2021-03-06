package com.sages.accountsuser.user.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

//@Entity
//@Table(name = "roles")
//@Getter
public
class UsersRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role name;
}
