package com.sparta.board.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostRequestDto {
    private final String title;
    private final String username;
    private final String contents;
}
