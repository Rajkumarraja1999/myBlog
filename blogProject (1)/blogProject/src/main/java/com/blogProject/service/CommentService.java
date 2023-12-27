package com.blogProject.service;

import com.blogProject.payLoad.CommentDto;

import java.util.List;

public interface CommentService {
     CommentDto createComment(long postId, CommentDto commentDto);

    void deleteCommentById(long postId, long commentId);
    List<CommentDto>getCommentByPostId(long postId);

    CommentDto updateComment(long postid,long commentId, CommentDto commentDto);
}
