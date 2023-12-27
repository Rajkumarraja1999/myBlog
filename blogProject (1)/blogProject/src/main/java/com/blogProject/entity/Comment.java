package com.blogProject.entity;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="email",nullable = false)
    private String email;
    @Column(name="body",nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "post_id")//for foreign key
    private Post post;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }
}
