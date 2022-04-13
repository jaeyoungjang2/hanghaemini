package com.sparta.hanghaemini.controller;

import com.sparta.hanghaemini.dto.JudgeSuccessDto;
import com.sparta.hanghaemini.dto.SignupRequestDto;
import com.sparta.hanghaemini.security.UserDetailsImpl;
import com.sparta.hanghaemini.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    // 로그인 페이지 요청
//    @GetMapping("/user/login")
//    public String login() {
//        System.out.println("로그인 페이지 요청");
//        return "login";
//    }
//
//    // 회원가입 페이지 요청
//    @GetMapping("/user/signup")
//    public String signup() {
//        System.out.println("회원가입 페이지 요청");
//        return "signup";
//    }

    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login2() {
        return "login";
    }

    // 로그아웃 시 메인페이지로 리다이렉트
    @GetMapping("/user/logout")
    public String logout() {
        System.out.println("로그 아웃");
        return "redirect:/";
    }

    // 회원가입 등록
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/user/signup")
    @ResponseBody
    public JudgeSuccessDto join(@Validated @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        // 유효성 검증을 통해 유효하지 않은 결과를 JudgeSuccessDto에 담아서 클라이언트에게 보내준다.
        if (bindingResult.hasErrors()) {
            // 유효성 검사에 실패했을 경우 Error를 리스트 형식으로 가져온다.
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());

            return new JudgeSuccessDto(false, errors.get(0));
            }
        return userService.save(requestDto);
    }

    // 로그인 실패시 동작
    @GetMapping("/login")
    public String loginError(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("testtest");
        return "login";
    }
}