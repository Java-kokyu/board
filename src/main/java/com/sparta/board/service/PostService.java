package com.sparta.board.service;

import com.sparta.board.domain.Post;
import com.sparta.board.domain.PostEditRequestDto;
import com.sparta.board.domain.PostRepository;
import com.sparta.board.domain.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostEditRequestDto requestDto){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        post.update(requestDto);
        return id;
    }

}

