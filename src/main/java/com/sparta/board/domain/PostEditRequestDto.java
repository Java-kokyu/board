package com.sparta.board.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PostEditRequestDto {
    private final String title;
    private final String contents;
}
