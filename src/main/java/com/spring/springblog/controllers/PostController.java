package com.spring.springblog.controllers;

import com.spring.springblog.models.Post;
import com.spring.springblog.models.User;
import com.spring.springblog.repositories.PostRepository;
import com.spring.springblog.repositories.UserRepository;
import com.spring.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postsDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository userDao, EmailService emailService) {
        this.postsDao = postsDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")

    public String postsIndex(Model model){
        List<Post> postList = postsDao.findAll();

        model.addAttribute("title", "All Posts");
        model.addAttribute("posts", postList);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")

    public String postView(Model model, @PathVariable long id){

        Post post = postsDao.getOne(id);

        model.addAttribute("post", post);

        User user = userDao.getOne(1L);

        model.addAttribute("user", user);

        return "posts/show";
    }


    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        postsDao.deleteById(id);

        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model){
        model.addAttribute("post", postsDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @ModelAttribute Post post){
        //TODO: Change user to logged in user dynamic
//        User user = usersDao.getOne(1L);
//        post.setAuthor(user);
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String postForm(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {

        User user = userDao.findAll().get(0);
        post.setUser(user);

        Post savedPost = postsDao.save(post);

//        Send an email once ad is saved

        String subject = "New Post Created!";

        String body = "Dear " + savedPost.getUser().getUsername() + ". Thank you for creating a post. Your post id is: " + savedPost.getId();

        emailService.prepareAndSend(savedPost, subject, body);

        return "redirect:/posts";
    }

//    @PostMapping("/posts/update")

//    @GetMapping("/posts/search")
//    @ResponseBody
//    public Post returnPostByTitle() {
//        return postsDao.findByTitle("First Post");
//    }
//
//    @GetMapping("/posts/order")
//    @ResponseBody
//    public List<Post> returnPostsByTitle() {
//        return postsDao.findByOrderByTitle();
//    }


}
