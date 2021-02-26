package com.spring.springblog.repositories;

import com.spring.springblog.models.Post;
import com.spring.springblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
