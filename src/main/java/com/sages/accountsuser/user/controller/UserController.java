package com.sages.accountsuser.user.controller;

import com.sages.accountsuser.user.domain.Role;
import com.sages.accountsuser.user.domain.User;
import com.sages.accountsuser.user.domain.UserCreate;
import com.sages.accountsuser.user.domain.UsersRole;
import com.sages.accountsuser.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.channels.AcceptPendingException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    private final PasswordEncoder passwordEncoder;


    @PostMapping("/foo")
    public ResponseEntity<String> foo() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addUser(@RequestBody UserCreate user) {
        String passwordEncode = passwordEncoder.encode(user.getPassword());
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(passwordEncode)
                .role(Role.USER)
                .build();
        service.addUser(newUser);
        return ResponseEntity.created(createdUri(newUser)).build();
    }


    private URI createdUri(User user) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/" + user.getId().toString()).build().toUri();
    }

    @GetMapping("id")
    public String showUserInfo(@PathVariable Long id) {
        Optional<User> userById = service.findById(id);
        return userById.get().getFirstName() + userById.get().getLastName();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll() {
        return service.listAllUsers();
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable Long id, @RequestBody User user) {
        service.update(id, user);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public List<User> usersListWithNoAdmin() {
        return service.listUsersWithNoAdmins();
    }


}
