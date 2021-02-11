package com.spring.springblog.repositories;

import com.spring.springblog.models.Dog;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DogRepository extends JpaRepository<Dog, Long> {
//    Dog findByName(String name);
//
//    List<Dog> findByOrderByName();

}