package com.sparta.hanghaemini.controller;

import com.sparta.hanghaemini.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    // 로그인 한 유저의 정보를 넘겨준다.
    public UserDetailsImpl home(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userDetails;

    }
}