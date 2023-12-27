package com.blogProject.payLoad;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min=3,message = "give a valid title")
    private String title;
    @NotEmpty
    @Size(min=3,message = "give a valid description")
    private String description;
    @NotEmpty
    @Size(min=3,message = "give a valid content")
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
