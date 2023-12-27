package com.blogProject.service;

import com.blogProject.payLoad.PostDto;

import java.util.List;

public interface PostService {

    PostDto savePost(PostDto postDto);

    void deletePost(long id);

    PostDto findPost(long id);

    PostDto updatePost(long id, PostDto postDto);

    List<PostDto> getAllPost();
}
