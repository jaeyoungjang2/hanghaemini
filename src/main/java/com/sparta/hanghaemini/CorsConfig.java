package com.sparta.hanghaemini;//package com.sparta.hanghaemini;
//
//import java.util.Arrays;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//public class CorsConfig {
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOriginPattern("*");
//        config.addAllowedOrigin("http://**");
//        config.setAllowedOrigins(Arrays.asList("*"));
//        config.setAllowCredentials(true);
//        config.addAllowedMethod("*");
//        config.addAllowedHeader("*");
//        // POST(Simple Request) 요청은 auto-generated Header 값 (= 기본 Header들) 말고는 access 불가
//        // 따라서 추가 Header들을 Client가 볼 수 있도록 설정해야 함
//        config.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers","Access-Control-Allow-Origin","Access-Control-Request-Method", "Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type", "Authorization"));
//        config.addExposedHeader("Access-Token");
//        config.addExposedHeader("Refresh-Token");
//        config.addExposedHeader("Access-Token-Expire-Time");
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//}