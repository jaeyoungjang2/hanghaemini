package com.sparta.hanghaemini.service;

import com.sparta.hanghaemini.dto.TeamDto;
import com.sparta.hanghaemini.dto.JudgeSuccessDto;
import com.sparta.hanghaemini.model.Post;
import com.sparta.hanghaemini.model.Team;
import com.sparta.hanghaemini.model.User;
import com.sparta.hanghaemini.repository.TeamRepository;
import com.sparta.hanghaemini.repository.PostRepository;
import com.sparta.hanghaemini.repository.UserRepository;
import com.sparta.hanghaemini.security.UserDetailsImpl;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

import static com.sparta.hanghaemini.exception.ExceptionMessages.*;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 그룹 참가 메서드
    @Transactional
    public JudgeSuccessDto join(Long teamId, UserDetailsImpl userDetails) {
        String message;
        Optional<Team> tempteam = teamRepository.findById(teamId);
        User loginedUser = userDetails.getUser();

        // 해당하는 그룹 아이디를 이용해서 그룹을 찾는다.
        if(!tempteam.isPresent()) {
            message = ILLEGAL_CANT_FOUND_team;
           return new JudgeSuccessDto(false, message);
        }

        System.out.println("loginedUser.getId() = " + loginedUser.getId());
        Team foundteam = tempteam.get();
        List<User> userList = foundteam.getUsers();

        // 현재 로그인된 사용자의 유저정보를 알아올 수 있는 방법은 UserDetails가 있다.
        // 그룹의 유저목록과 비교해서 같은 사람이면 중복을 알려준다.
        for(User user: userList) {
            System.out.println("user.getId() = " + user.getId());
            //중복 검사 : 비교 대상이 로그인 한 유저와 그룹안의 유저리스트의 유저와 비교해야한다.
            if(user.getId().equals(loginedUser.getId())) {
                message = ILLEGAL_ALREADY_JOINED_team;
                return new JudgeSuccessDto(false, message);
            }
        }

        // 해당하는 그룹의 max & cur을 찾아서 로직을 구현한다.
        Long maxTeamCnt = foundteam.getMaxTeamOf();
        Long curTeamCnt = foundteam.getCurTeamCnt();

        if(maxTeamCnt > curTeamCnt) {
            ++curTeamCnt;
            foundteam.changeCur(curTeamCnt);
            // 그룹의 참가 유저 List에 로그인한 유저를 추가해준다.
            foundteam.getUsers().add(loginedUser);

            User user = userRepository.findById(loginedUser.getId()).get();
            user.changeTeam(foundteam);
//            teamRepository.save(foundteam);

        } else {
            message = ILLEGAL_EXCEEDED_ALLOWED_MAXCOUNT;
            return new JudgeSuccessDto(false, message);
        }
        return new JudgeSuccessDto(true, "참가 완료");
    }


    // 그룹 취소 메서드
    @Transactional
    public JudgeSuccessDto withdrawal(Long teamId, UserDetailsImpl userDetails) {
        String message;
        Optional<Team> tempteam = teamRepository.findById(teamId);
        User loginedUser = userDetails.getUser();

        // 해당하는 그룹 아이디를 이용해서 그룹을 찾는다.
        if(!tempteam.isPresent()) {
            message = ILLEGAL_CANT_FOUND_team;
            return new JudgeSuccessDto(false, message);
        }

        Team foundteam = tempteam.get();
        List<User> userList = foundteam.getUsers();

        Long maxTeamCnt = foundteam.getMaxTeamOf();
        Long curTeamCnt = foundteam.getCurTeamCnt();

        // 현재 로그인된 사용자의 유저정보를 알아올 수 있는 방법은 UserDetails가 있다.
        // 그룹의 유저목록과 비교해서 같은 사람이면 중복을 알려준다.
        for(User user: userList) {
            System.out.println("user.getId() = " + user.getId());
            //중복 검사 : 비교 대상이 로그인 한 유저와 그룹안의 유저리스트의 유저와 비교해야한다.
            if(user.getId().equals(loginedUser.getId())) {
                --curTeamCnt;
                foundteam.changeCur(curTeamCnt);
                // 그룹의 참가 유저 List에 로그인한 유저를 삭제해준다.
                foundteam.getUsers().remove(loginedUser);

                User loginUser = userRepository.findById(loginedUser.getId()).get();
                loginUser.removeTeam(foundteam);
                return new JudgeSuccessDto(true, "취소 완료");
            }
        }
        message = ILLEGAL_ALREADY_LEFT_team;
        return new JudgeSuccessDto(false, message);


//            teamRepository.save(foundteam);


    }
}