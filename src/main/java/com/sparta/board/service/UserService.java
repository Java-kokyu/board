package com.sparta.board.service;

import com.sparta.board.dto.SignupRequestDto;
import com.sparta.board.model.UserInfo;
import com.sparta.board.model.UserRoleEnum;
import com.sparta.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void registerUser(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
// 회원 ID 중복 확인

        //password 암호화
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        UserRoleEnum role = UserRoleEnum.USER;

        UserInfo userInfo = new UserInfo(username,password, role);

        userRepository.save(userInfo);
    }

}
