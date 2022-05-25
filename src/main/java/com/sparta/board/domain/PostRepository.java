package com.sparta.board.domain;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    //    List<Post> findAllByOrderByModifiedAtDesc(); //수정시간 순으로 정렬
    Page<Post> findAllByOrderByModifiedAtDesc(Pageable pageable);

    List<Post> findByTitleContains(String title);


}
