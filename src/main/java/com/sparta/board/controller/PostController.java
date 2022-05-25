package com.sparta.board.controller;

import com.sparta.board.domain.Post;
import com.sparta.board.domain.PostRepository;
import com.sparta.board.domain.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto){
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }



    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
        return id;
    }


}
