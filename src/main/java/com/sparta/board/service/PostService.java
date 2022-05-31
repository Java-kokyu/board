package com.sparta.board.service;

import com.sparta.board.dto.PostRequestDto;
import com.sparta.board.model.Post;
import com.sparta.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostRequestDto requestDto){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        post.update(requestDto);
        return id;
    }

}

