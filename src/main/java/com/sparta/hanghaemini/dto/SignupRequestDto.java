package com.sparta.hanghaemini.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignupRequestDto {
    // @Controller에 있는 SignupResquestDto를 파라미터로 갖는 메서드에 @Validated 어노테이션을 사용
    @NotBlank(message = "유저이름을 입력해 주세요!")
    @Size(min = 4, max = 10, message = "4자 이상 10자 이하의 값을 입력해 주세요!")
//    @Pattern(regexp = "^[A-Za-z]{1}[A-Za-z0-9_-]{3,19}$", message = "영문으로 시작 및 숫자+언더바/하이픈만 입력해 주세요!")
    private String username;

    @NotBlank(message = "닉네임을 입력해 주세요!")
    @Size(min = 2, max = 12, message = "2자 이상 12자 이하의 값을 입력해 주세요!")
//    @Pattern(regexp = "^[가-힣a-zA-Z]+$", message = "한글과 영문만 입력해 주세요!")
    private String nickname;

    @NotBlank(message = "패스워드를 입력해 주세요!")
//    @Pattern(regexp = "^(?=.*[a-zA-Z])((?=.*\\d)(?=.*\\W)).{8,16}$", message = "영문/숫자/특수문자(!@#$%^&*)를 포함하여 8~16자로 입력해야합니다")
    private String password;

}
