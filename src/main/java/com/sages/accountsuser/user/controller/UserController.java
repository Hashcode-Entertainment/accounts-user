package com.sages.accountsuser.user.controller;

import com.sages.accountsuser.user.domain.User;
import com.sages.accountsuser.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/{firstName}/{lastName}")
    @ResponseBody
    public String addUser(@PathVariable String firstName, @PathVariable String lastName) {
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        service.addUser(user);
        return "user added";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return "user deleted";
    }

    @GetMapping("/all")
    public String findAll() {
        return String.valueOf(service.listAllUsers());
    }

}
