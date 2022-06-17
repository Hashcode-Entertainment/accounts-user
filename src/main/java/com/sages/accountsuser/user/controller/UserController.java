package com.sages.accountsuser.user.controller;

import com.sages.accountsuser.user.domain.User;
import com.sages.accountsuser.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.channels.AcceptPendingException;
import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
        service.addUser(newUser);
        return ResponseEntity.created(createdUri(newUser)).build();
    }

    private URI createdUri(User user) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/" + user.getId().toString()).build().toUri();
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

}
