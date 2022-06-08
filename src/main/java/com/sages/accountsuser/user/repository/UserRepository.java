package com.sages.accountsuser.user.repository;

import com.sages.accountsuser.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
