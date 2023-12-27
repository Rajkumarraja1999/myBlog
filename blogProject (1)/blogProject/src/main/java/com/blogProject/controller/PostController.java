package com.blogProject.controller;

import com.blogProject.payLoad.PostDto;
import com.blogProject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/blog")

public class PostController {
    @Autowired
   private PostService postService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?>create(@Valid @RequestBody PostDto postDto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto pd = postService.savePost(postDto);
        return new ResponseEntity<>(pd,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String>delete(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>(" Post deleted!",HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PostDto>>getAll(){
        List<PostDto>dto=postService.getAllPost();
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<PostDto>find(@PathVariable long id){
        PostDto pd = postService.findPost(id);
        return new ResponseEntity<>(pd,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<PostDto> update(@PathVariable long id,@RequestBody PostDto postDto){
        PostDto pd=postService.updatePost(id,postDto);
        return new ResponseEntity<>(pd,HttpStatus.OK);
    }



}

