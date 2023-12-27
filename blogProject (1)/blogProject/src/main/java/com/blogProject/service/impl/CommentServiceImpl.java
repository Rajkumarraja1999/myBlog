package com.blogProject.service.impl;

import com.blogProject.entity.Comment;
import com.blogProject.entity.Post;
import com.blogProject.exception.ResourceNotFound;
import com.blogProject.payLoad.CommentDto;
import com.blogProject.repository.CommentRepository;
import com.blogProject.repository.PostRepository;
import com.blogProject.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFound("post not found with id= " + postId)
        );
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment saveComment = commentRepo.save(comment);
        CommentDto dto = mapToDto(saveComment);
        return dto;


    }

    @Override
    public void deleteCommentById(long postId,long commentId) {
        postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFound("Post not Found with id= "+postId)
        );
        commentRepo.findById(commentId).orElseThrow(
                () -> new ResourceNotFound("Comments not Found with id= " + commentId)

        );
        commentRepo.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        postRepo.findById(postId).orElseThrow(
                ()->new ResourceNotFound("Post not Found with id= "+postId)
        );
        List<Comment>comments = commentRepo.findByPostId(postId);
        List<CommentDto> dto = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return dto;

    }

    @Override
    public CommentDto updateComment(long postId,long commentId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFound("post not found with id= " + postId)
        );
        Comment comment = commentRepo.findById(commentId).orElseThrow(
                () -> new ResourceNotFound("Comments not Found with id= " + commentId)

        );
        comment.setBody(commentDto.getBody());
        comment.setName(comment.getName());
        comment.setEmail(commentDto.getEmail());
        Comment saveComment = commentRepo.save(comment);
        CommentDto dto = mapToDto(saveComment);
        return dto;
    }

    Comment mapToEntity(CommentDto commentDto){
        Comment com = modelMapper.map(commentDto, Comment.class);
//        Comment com=new Comment();
//        com.setName(commentDto.getName());
//        com.setEmail(commentDto.getEmail());
//        com.setBody(commentDto.getBody());
        return com;

    }
    CommentDto mapToDto(Comment saveComment){
        CommentDto dto = modelMapper.map(saveComment, CommentDto.class);
//        CommentDto dto=new CommentDto();
//        dto.setId(saveComment.getId());
//        dto.setName(saveComment.getName());
//        dto.setEmail(saveComment.getEmail());
//        dto.setBody(saveComment.getBody());
        return dto;

    }
}
