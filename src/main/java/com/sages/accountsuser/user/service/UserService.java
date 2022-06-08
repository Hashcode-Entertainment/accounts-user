package com.sages.accountsuser.user.service;

import com.sages.accountsuser.user.domain.User;
import com.sages.accountsuser.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> listAllUsers() {
        return repository.findAll();
    }

    public void addUser(User user){
        repository.save(user);
    }

    public void deleteUser(Long id){
        repository.deleteById(id);
    }



}
