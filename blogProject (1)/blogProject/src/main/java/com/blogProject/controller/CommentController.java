package com.blogProject.controller;

import com.blogProject.payLoad.CommentDto;
import com.blogProject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comment")

public class CommentController {

    @Autowired
    private CommentService comService;

    @PostMapping("{postId}")
    public ResponseEntity<CommentDto>createComment(
            @PathVariable long postId,
            @RequestBody CommentDto commentDto
    ){
        CommentDto dto = comService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/comment/postId/commentId
    @DeleteMapping("{postId}/{commentId}")
    public ResponseEntity<String>deleteComment(
            @PathVariable long postId,
            @PathVariable long commentId
    ){
        comService.deleteCommentById(postId,
                commentId);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }
    //http://localhost:8080/api/comment/postId
    @GetMapping("{postId}")
    public List<CommentDto>getCommentByPostid(@PathVariable long postId){
        List<CommentDto> commentDto = comService.getCommentByPostId(postId);
        return commentDto;
    }
    @PutMapping("{postId}/{commentId}")
    public ResponseEntity<CommentDto>updateComment(@PathVariable long postId,@PathVariable long commentId,@RequestBody CommentDto commentDto){
       CommentDto dto= comService.updateComment(postId,commentId,commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
