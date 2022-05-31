package com.sparta.board.controller;

import com.sparta.board.dto.PostRequestDto;
import com.sparta.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostDetailController {
    private final PostService postService;



    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.update(id, requestDto);
    }
}
