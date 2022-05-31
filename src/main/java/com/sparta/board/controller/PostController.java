package com.sparta.board.controller;

import com.sparta.board.model.Post;
import com.sparta.board.repository.PostRepository;
import com.sparta.board.dto.PostRequestDto;
import com.sparta.board.security.UserDetailsImpl;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
            String writer = userDetails.getUsername();
            Post post = new Post(requestDto, writer);
            return postRepository.save(post);
    }

    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUsername();
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 계정입니다.")
        );
        if(post.getWriter().equals(username)) postRepository.deleteById(id);
        else throw new IllegalArgumentException("계정이 일치하지 않습니다.");
        return id;
    }


}
