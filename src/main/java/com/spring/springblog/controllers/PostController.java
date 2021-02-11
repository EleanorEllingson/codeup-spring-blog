package com.spring.springblog.controllers;

import com.spring.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {


    @GetMapping("/posts")
    public String postsIndex(Model model){
        Post post1 = new Post("First Post", "This is my first post", 1);
        Post post2 = new Post("Second Post", "This is my second post", 2);
        Post post3 = new Post("Third Post", "This is my third post", 3);

        List<Post> postList = new ArrayList<>();
        postList.add(post1);
        postList.add(post2);
        postList.add(post3);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")

    public String postView(Model model){
        Post post = new Post("First Post", "This is my first post", 1);
        model.addAttribute("title", "Single Posts");
        model.addAttribute("post", post);
        return "posts/show";
    }


    @GetMapping("/posts/create")
    @ResponseBody
    public String create(){
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String postCreate(){
        return "create a new post";
    }


}
