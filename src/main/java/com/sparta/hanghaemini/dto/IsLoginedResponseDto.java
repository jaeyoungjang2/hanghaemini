package com.sparta.hanghaemini.dto;

import lombok.Getter;

@Getter
public class IsLoginedResponseDto {
    private boolean ok;
    private String nickname;

    public IsLoginedResponseDto(boolean ok, String nickname) {
        this.ok = ok;
        this.nickname = nickname;
    }

    public IsLoginedResponseDto(boolean ok) {
        this.ok = ok;
    }
}