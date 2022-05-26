package com.sparta.board.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class PostEditRequestDto {
    private final String title;
    private final String contents;
}
