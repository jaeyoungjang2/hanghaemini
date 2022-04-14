package com.sparta.hanghaemini.controller;

import com.sparta.hanghaemini.dto.JudgeSuccessDto;
import com.sparta.hanghaemini.security.UserDetailsImpl;
import com.sparta.hanghaemini.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TeamController {

    private final TeamService teamService;

    @PutMapping("/api/posts/in/{teamId}")
    public JudgeSuccessDto joinTeam(@PathVariable Long teamId, @AuthenticationPrincipal UserDetailsImpl userDetails ) {
        return teamService.join(teamId, userDetails);
    }

    @PutMapping("/api/posts/out/{teamId}")
    public JudgeSuccessDto withdrawal(@PathVariable Long teamId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return teamService.withdrawal(teamId, userDetails);
    }
}
