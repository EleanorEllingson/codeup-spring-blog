package com.spring.springblog.repositories;

import com.spring.springblog.models.Dog;
import com.spring.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
