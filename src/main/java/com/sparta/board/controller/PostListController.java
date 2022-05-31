package com.sparta.board.controller;

import com.sparta.board.model.Post;
import com.sparta.board.repository.PostRepository;
import com.sparta.board.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PostListController {

    private final PostRepository postRepository;

    @GetMapping("/posts")
    public String getPosts(Model model, @PageableDefault(size = 10) Pageable pageable, @AuthenticationPrincipal UserDetailsImpl userDetails,
                           @RequestParam(required = false, defaultValue = "") String search){
        Page<Post> posts = postRepository.findByTitleContainingOrContentsContainingOrderByModifiedAtDesc(search, search, pageable);
        int currentPage = posts.getPageable().getPageNumber();
        int totalPage = posts.getTotalPages();
        int startPage = Math.max(1, currentPage - 1);
        int endPage = Math.min(totalPage, (currentPage == 0)? currentPage + 3 : (currentPage == 1)? currentPage + 2 : currentPage + 1);
        long totalPost = posts.getTotalElements();

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("posts", posts);
        model.addAttribute("totalPost", totalPost);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("username", userDetails.getUsername());

        return "index";
    }

    @GetMapping("/posts/{id}")
    public String showPost(Model model, @PathVariable Long id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );

        model.addAttribute("title", post.getTitle());
        model.addAttribute("username", post.getUsername());
        model.addAttribute("modifiedAt", post.getModifiedAt());
        model.addAttribute("contents", post.getContents());
        return "post";
    }
}
