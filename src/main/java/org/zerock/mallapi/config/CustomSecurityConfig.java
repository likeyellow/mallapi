package org.zerock.mallapi.config;


import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.RequestContextFilter;
import org.zerock.mallapi.security.filter.JWTCheckFilter;
import org.zerock.mallapi.security.handler.APILoginFailHandler;
import org.zerock.mallapi.security.handler.APILoginSuccessHandler;
import org.zerock.mallapi.security.handler.CustomAccessDeniedHandler;
import org.zerock.mallapi.security.handler.CustomAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableMethodSecurity
public class CustomSecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        log.info("------------------------security config------------------------");

        // CORS 설정
        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
        });
        
        // 세션 관리 설정
        http.sessionManagement(sessionConfig -> sessionConfig.
            sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // CSRF 설정 비활성화    
        http.csrf(config -> config.disable());

        // JWT 필터 추가
        http.addFilterBefore(new JWTCheckFilter(),
        UsernamePasswordAuthenticationFilter.class); // JWT체크

        // JWTCheckFilter 뒤에 RequestContextFilter 추가
        // (이게 있어야 파라미터 없이 HttpServletRequest받아올 수 있음)
        http.addFilterAfter(new RequestContextFilter(), JWTCheckFilter.class);

        // 인증되지 않은 사용자가 리소스에 접근했을 때 수행되는 핸들러를 등록
        http.exceptionHandling(exceptionHandling -> {
            exceptionHandling.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
            exceptionHandling.accessDeniedHandler(new CustomAccessDeniedHandler());
        });

        // formLogin() 인증이 필요한 요청은 스프링 시큐리티에서 사용하는 기본 Form Login Page 사용
        http.formLogin(config -> {
            config.loginPage("/member/login");
            config.loginProcessingUrl("/api/member/login");
            config.successHandler(new APILoginSuccessHandler());
            config.failureHandler(new APILoginFailHandler());
        });

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() { // CORS 설정에 필요한 메서드
        
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
