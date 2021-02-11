package com.spring.springblog.controllers;

import com.spring.springblog.models.Dog;
import com.spring.springblog.repositories.DogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DogController {

    private final DogRepository dogsDao;

    public DogController(DogRepository dogsDao) {
        this.dogsDao = dogsDao;
    }

    @GetMapping("/ads/jpa")
    @ResponseBody
    public List<Dog> jpaIndex() {
        return dogsDao.findAll();
    }

//    returns the ad id
    @GetMapping("/dogs/jpa/{id}")
    @ResponseBody
    public String viewJpaAd(@PathVariable long id) {
        return dogsDao.getOne(id).toString();
    }

    @GetMapping("dogs/{id}")
    public String viewDog(@PathVariable long id, Model model) {
        Dog myDog = dogsDao.getOne(id);
        model.addAttribute("dog", myDog);

        return "dogs/show";
    }

//    @GetMapping("/dogs/jpa/create")
//    public  void createDog();
//    Dog dog = new Dog();
//    dog.setName()

}
