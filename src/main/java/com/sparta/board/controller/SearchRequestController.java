package com.sparta.board.controller;

import com.sparta.board.domain.Post;
import com.sparta.board.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchRequestController {
    private final PostRepository postRepository;

    @GetMapping("/api/search")
    public List<Post> getSearchedPost(@RequestParam String query) {
        List<Post> postList = postRepository.findByTitleContains(query);
        return postList;
    }
}
