package com.sages.accountsuser.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/login")
    public String test(){
        return "Zalogowano";
    }

}
