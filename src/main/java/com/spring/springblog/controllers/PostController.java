package com.spring.springblog.controllers;

import com.spring.springblog.models.Post;
import com.spring.springblog.models.User;
import com.spring.springblog.repositories.PostRepository;
import com.spring.springblog.repositories.UserRepository;
import com.spring.springblog.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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

        User user = userDao.getOne(id);

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
        postsDao.save(post);
        return "redirect:/posts";
    }



    @Value("${file-upload-path}")
    private String uploadPath;



    @GetMapping("/posts/create")
    public String postForm(Model model){
        model.addAttribute("post", new Post());

        return "posts/create";
    }




    @PostMapping("/posts/create")
    public String saveFile(@ModelAttribute Post post,
            @RequestParam(name = "file") MultipartFile uploadedFile,
            Model model
    ) {
        if(uploadedFile != null) {
            String filename = uploadedFile.getOriginalFilename();
            String filepath = Paths.get(uploadPath, filename).toString();
            File destinationFile = new File(filepath);

            try {
                uploadedFile.transferTo(destinationFile);


                post.setUploadedFilePath("/uploads/" + filename);

            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "Oops! Something went wrong! " + e);
            }
        }
        post.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Post savedPost = postsDao.save(post);
        String subject = "New Post Created!";

        String body = "Dear " + savedPost.getUser().getUsername() + ". Thank you for creating a post. Your post id is: " + savedPost.getId();

        emailService.prepareAndSend(savedPost, subject, body);
        model.addAttribute("message", "File successfully uploaded!");
        return "redirect:/posts";
    }




}
