package com.sparta.board.model;


import com.sparta.board.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Post extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String contents;

    public Post(PostRequestDto requestDto, String username){
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.writer = username;
    }

    public void update(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}