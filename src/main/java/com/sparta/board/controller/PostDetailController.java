package com.sparta.board.controller;


import com.sparta.board.dto.PostRequestDto;
import com.sparta.board.model.Post;
import com.sparta.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequiredArgsConstructor
public class PostDetailController {

    private final PostRepository postRepository;

    //db에서 사용자 확인
    //비로그인 회원 접근 불가
    @PostMapping("/posts/edit/{id}")
    public String checkEditable(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, Model model){
        model.addAttribute("title", postRequestDto.getTitle());
        model.addAttribute("contents", postRequestDto.getContents());
        String url = "/posts/edit/" + id;
        return "redirect:" + url;
    }

    //post Mapping에서 정보 보내주기.
    @GetMapping("/posts/edit/{id}")
    public String PostEdit(@PathVariable Long id, Model model){
        Post post= postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 게시글을 찾을 수 없습니다.")
        );
        model.addAttribute("id", id);
        model.addAttribute("title", post.getTitle());
        model.addAttribute("contents", post.getContents());
        return "postEdit";
    }
}
//        Post post = postRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("작성자가 존재하지 않습니다.")
//        );
//        String writer = post.getWriter();
//
//        if(!username.equals(writer)) throw new IllegalArgumentException("계정이 일치하지 않습니다");
