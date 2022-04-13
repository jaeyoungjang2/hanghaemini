package com.sparta.hanghaemini.service;

import com.sparta.hanghaemini.dto.JudgeSuccessDto;
import com.sparta.hanghaemini.dto.SignupRequestDto;
import com.sparta.hanghaemini.model.User;
import com.sparta.hanghaemini.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.sparta.hanghaemini.exception.ExceptionMessages.*;


@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public JudgeSuccessDto save(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String nickname = requestDto.getNickname();
        String message;
        Optional<User> found = userRepository.findByUsername(username);

        // username 중복 체크
        if(validatedDuplicateUsername(found)) {
            message = ILLEGAL_USER_NAME_DUPLICATION;
            return new JudgeSuccessDto(false, message);
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, nickname, password);
        userRepository.save(user);
        message = "회원가입 성공";
        return new JudgeSuccessDto(true, message);
    }

    // 유저이름 중복 체크
    private boolean validatedDuplicateUsername(Optional<User> found) {
        return found.isPresent() ? true : false;
    }
}
