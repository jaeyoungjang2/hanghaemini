package com.sparta.hanghaemini.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.hanghaemini.dto.JudgeSuccessDto;
import com.sparta.hanghaemini.security.jwt.JwtTokenUtils;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class FormLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_TYPE = "BEARER";

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication)
        throws IOException {
        final UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());
        // Token 생성
        final String token = JwtTokenUtils.generateJwtToken(userDetails);

        System.out.println(token);

        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8"); // HelloData 객체
        JudgeSuccessDto loginInfo = new JudgeSuccessDto(true,"로그인 성공");


        String result = mapper.writeValueAsString(loginInfo);
        response.getWriter().write(result);
        response.addHeader(AUTH_HEADER, TOKEN_TYPE + " " + token);
    }
}
