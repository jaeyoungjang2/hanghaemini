package com.sparta.hanghaemini.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class JudgeSuccessDto {
    private boolean ok;
    private String message;

    public JudgeSuccessDto(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }
}
