package com.blogProject.payLoad;

public class CommentDto {
    private long id;
    private String email,name,body;
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
}
