package com.sparta.board.controller;

import com.sparta.board.domain.Post;
import com.sparta.board.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PostSSRController {

    private final PostRepository postRepository;

    @GetMapping("/")
    public String getPosts(Model model, @PageableDefault(size = 10) Pageable pageable,
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

        return "index";
    }
}
