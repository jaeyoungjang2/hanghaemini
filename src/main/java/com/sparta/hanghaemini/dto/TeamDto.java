package com.sparta.hanghaemini.dto;

import com.sparta.hanghaemini.model.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TeamDto {
    private Long maxTeamOf;
    private Long curTeamCnt = 1L;

    public TeamDto(Team team) {
        this.maxTeamOf = team.getMaxTeamOf();
        this.curTeamCnt = team.getCurTeamCnt();
    }

    public TeamDto(Long maxTeamOf, Long curTeamCnt) {
        this.maxTeamOf = maxTeamOf;
        this.curTeamCnt = curTeamCnt;
    }
}

