package com.sparta.board.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostPwdCheckDto {
    private final Long id;
    private final String password;
}
