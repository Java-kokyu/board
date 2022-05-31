package com.sparta.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@NoArgsConstructor
public class SignupRequestDto {
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="^[a-zA-Z0-9]{3,15}$",
            message = "비밀번호는 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성된 비밀번호여야 합니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    private String password2;
}
