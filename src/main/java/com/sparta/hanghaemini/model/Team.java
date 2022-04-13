package com.sparta.hanghaemini.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sparta.hanghaemini.dto.TeamDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @OneToMany
    private List<User> users = new ArrayList<>();

    // 스터디 참여 정원 수
    private Long maxTeamOf;

    // 현재 스터디원 수
    private Long curTeamCnt;

    public Team(Long maxTeamOf, Long curTeamCnt) {
        this.maxTeamOf = maxTeamOf;
        this.curTeamCnt = curTeamCnt;
    }

    public Team(TeamDto teamDto) {
        this.maxTeamOf = teamDto.getMaxTeamOf();
        this.curTeamCnt = teamDto.getCurTeamCnt();
    }

    public Team(Long maxTeamOf) {
        this.maxTeamOf = maxTeamOf;
    }

    public void changeMax(Long maxTeamOf) {
        this.maxTeamOf = maxTeamOf;
    }
}
