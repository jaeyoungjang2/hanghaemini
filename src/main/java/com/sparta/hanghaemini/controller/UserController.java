package com.sparta.hanghaemini.controller;

import com.sparta.hanghaemini.dto.IdCheckDto;
import com.sparta.hanghaemini.dto.IsLoginedResponseDto;
import com.sparta.hanghaemini.dto.JudgeSuccessDto;
import com.sparta.hanghaemini.dto.SignupRequestDto;
import com.sparta.hanghaemini.dto.UserRequestDto;
import com.sparta.hanghaemini.repository.UserRepository;
import com.sparta.hanghaemini.security.UserDetailsImpl;
import com.sparta.hanghaemini.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    // 회원가입 등록
    @PostMapping("/user/signup")
    public JudgeSuccessDto join(
        @Validated @RequestBody SignupRequestDto requestDto,
        BindingResult bindingResult) {
        // 유효성 검증을 통해 유효하지 않은 결과를 JudgeSuccessDto에 담아서 클라이언트에게 보내준다.
        if (bindingResult.hasErrors()) {
            // 유효성 검사에 실패했을 경우 Error를 리스트 형식으로 가져온다.
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());

            return new JudgeSuccessDto(false, errors.get(0));
            }
        return userService.save(requestDto);
    }


    // 회원 로그인 여부 확인
    @GetMapping("/api/isLogin")
    public static IsLoginedResponseDto isLogin(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new IsLoginedResponseDto(true, userDetails.getUser().getNickname());
    }

    @PostMapping("/api/idCheck")
    public JudgeSuccessDto idCheck(@RequestBody IdCheckDto idCheckDto) {
        if (userRepository.findByNickname(idCheckDto.getNickname()).isEmpty()) {
            return new JudgeSuccessDto(false, "이미 존재하는 id입니다.");
        }
        return new JudgeSuccessDto(true, "사용할 수 있는 id입니다.");
    }
}