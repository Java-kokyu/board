package com.sparta.board.controller;

import com.sparta.board.domain.Post;
import com.sparta.board.domain.PostRepository;
import com.sparta.board.domain.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostRepository postRepository;

    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto){
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    @GetMapping("/")
    public String getPosts(Model model, @PageableDefault(size = 10)Pageable pageable){
        Page<Post> posts = postRepository.findAllByOrderByModifiedAtDesc(pageable);
        int startPage = Math.max(1, posts.getPageable().getPageNumber() - 3);
        int endPage = Math.min(posts.getTotalPages(), posts.getPageable().getPageNumber() + 3);
        long totalPost = posts.getTotalElements();



        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("posts", posts);
        model.addAttribute("totalPost", totalPost);

        return "index";
    }

    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
        return id;
    }


}
