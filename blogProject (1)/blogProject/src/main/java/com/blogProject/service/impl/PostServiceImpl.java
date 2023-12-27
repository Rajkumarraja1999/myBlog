package com.blogProject.service.impl;

import com.blogProject.entity.Post;
import com.blogProject.exception.ResourceNotFound;
import com.blogProject.payLoad.PostDto;
import com.blogProject.repository.PostRepository;
import com.blogProject.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto savePost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savedPost = postRepo.save(post);
        PostDto dto = mapToDto(savedPost);
        return dto;
    }

    @Override
    public void deletePost(long id) {
         postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFound("post not found with id: "+id)

        );
        postRepo.deleteById(id);


    }

    @Override
    public PostDto findPost(long id) {

        Post post = postRepo.findById(id).orElseThrow(
                ()->new ResourceNotFound("post not found with id" +id)
        );
        PostDto dto = mapToDto(post);
        return dto;
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFound("post not found with Id" + id)
        );
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post savedPost = postRepo.save(post);
        PostDto dto = mapToDto(savedPost);
        return dto;
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> all = postRepo.findAll();
        List<PostDto> collect = all.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return collect;
    }

    Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);


//        Post post=new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;


    }
   PostDto mapToDto(Post savedPost){
       PostDto dto = modelMapper.map(savedPost, PostDto.class);
//        PostDto postDto=new PostDto();
//        postDto.setId(savedPost.getId());
//        postDto.setTitle(savedPost.getTitle());
//        postDto.setDescription(savedPost.getDescription());
//        postDto.setContent(savedPost.getContent());
        return dto;
   }
}
