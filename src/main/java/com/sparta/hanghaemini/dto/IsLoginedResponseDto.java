package com.sparta.hanghaemini.dto;

import lombok.Getter;

@Getter
public class IsLoginedResponseDto {
    private boolean ok;
    private String username;

    public IsLoginedResponseDto(boolean ok, String username) {
        this.ok = ok;
        this.username = username;
    }

    public IsLoginedResponseDto(boolean ok) {
        this.ok = ok;
    }
}