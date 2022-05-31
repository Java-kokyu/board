package com.sparta.board.repository;

import com.sparta.board.model.Post;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByTitleContainingOrContentsContainingOrderByModifiedAtDesc(String title, String contents, Pageable pageable);



}
