package com.sparta.board.controller;

import com.sparta.board.dto.SignupRequestDto;
import com.sparta.board.dto.UserInfoDto;
import com.sparta.board.model.UserInfo;
import com.sparta.board.model.UserRoleEnum;
import com.sparta.board.repository.UserRepository;
import com.sparta.board.security.UserDetailsImpl;
import com.sparta.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(Model model) {
        model.addAttribute("signupRequestDto", new SignupRequestDto());
        return "signup";
    }

    @PostMapping("/user/userinfo")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new UserInfoDto(userDetails.getUsername());
    }


    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@Valid @ModelAttribute SignupRequestDto signupRequestDto, BindingResult bindingResult) {

        Optional<UserInfo> found = userRepository.findByUsername(signupRequestDto.getUsername());
        if(found.isPresent()){
            FieldError fieldError = new FieldError("signupRequestDto", "username", "이미 존재하는 아이디 입니다.");
            bindingResult.addError(fieldError);
        }

        if(!signupRequestDto.getPassword().equals(signupRequestDto.getPassword2())){
            FieldError fieldError = new FieldError("signupRequestDto", "password2", "비밀번호가 일치하지 않습니다.");
            bindingResult.addError(fieldError);
        }

        if(signupRequestDto.getPassword().indexOf(signupRequestDto.getUsername()) != -1){
            FieldError fieldError = new FieldError("signupRequestDto", "password", "비밀번호에 닉네임과 같은 값을 넣을 수 없습니다.");
            bindingResult.addError(fieldError);
        }

        if(bindingResult.hasErrors()){
            return "signup";
        }

        userService.registerUser(signupRequestDto);
        return "redirect:/user/loginView";
    }
}
