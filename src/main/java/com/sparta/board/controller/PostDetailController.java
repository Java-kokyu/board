package com.sparta.board.controller;

import com.sparta.board.domain.Post;
import com.sparta.board.domain.PostEditRequestDto;
import com.sparta.board.domain.PostPwdCheckDto;
import com.sparta.board.domain.PostRepository;
import com.sparta.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostDetailController {
    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/api/posts/{id}")
    public Post showPost(@PathVariable Long id){
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
    }

    @PatchMapping("/api/posts/{id}/checkPassword")
    public PostEditRequestDto checkPassword(@PathVariable Long id, @RequestBody PostPwdCheckDto postPwdCheckDto){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );

        PostEditRequestDto postEditRequestDto = new PostEditRequestDto(post.getTitle(), post.getContents());

        if(!post.getPassword().equals(postPwdCheckDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return postEditRequestDto;
    }

    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostEditRequestDto requestDto){
        return postService.update(id, requestDto);
    }
}
