package com.sages.accountsuser.user.service;

import com.sages.accountsuser.user.domain.Role;
import com.sages.accountsuser.user.domain.User;
import com.sages.accountsuser.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> listAllUsers() {
        return repository.findAll();
    }

    public List<User> listUsersWithNoAdmins() {
        List<User> usersListWithNoAdmins = new ArrayList<>();
        for (User u : listAllUsers()) {
            if (u.getRole().toString().equals(Role.USER.toString())) {
                usersListWithNoAdmins.add(u);
            }
        }
        return usersListWithNoAdmins;
    }

    public void addUser(User user) {
        repository.save(user);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

//    public Optional<User> findByUsername(String userName) {
//        return repository.findByUsername(userName);
//    }

    @Transactional
    public User update(Long id, User user) {
        Optional<User> byId = repository.findById(id);
        User updateUser = byId.get();
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        return updateUser;
    }
}
