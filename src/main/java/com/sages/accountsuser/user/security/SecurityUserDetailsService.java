//package com.sages.accountsuser.user.security;
//
//import com.sages.accountsuser.user.domain.User;
//import com.sages.accountsuser.user.service.UserService;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@AllArgsConstructor
//public class SecurityUserDetailsService implements UserDetailsService {
//
//    private final UserService service;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = service.findByUsername(username);
//        return user;
//    }
//}


