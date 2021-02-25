package com.spring.springblog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11) UNSIGNED")
    private long id;

    @Column(nullable = false)
        private String title;

    @Column(nullable = false)
        private String body;

    @Column
        private String uploadedFilePath;

    @ManyToOne
    @JoinColumn (name = "username_id")
    private User user;



    public Post() {
    }

    public String getUploadedFilePath() {
        return uploadedFilePath;
    }

    public void setUploadedFilePath(String uploadedFilePath) {
        this.uploadedFilePath = uploadedFilePath;
    }

    public Post(String title, String body, long id, String uploadedFilePath) {
        this.title = title;
        this.body = body;
        this.uploadedFilePath = uploadedFilePath;
        this.id = id;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


//    public void setUser(User user) {
//        this.user = user;
//    }
}
