package org.zerock.mallapi.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.zerock.mallapi.domain.ErrorCode;
import org.zerock.mallapi.util.MemberException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        
        log.error("Authentication Error: {}", authException.getMessage());   

        // JWT 인증이 실패했을 때(=JWTCheckFilter 에서 AuthenticationException 에러가 발생했을 때) 처리로직
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        MemberException e = new MemberException(ErrorCode.INVALID_TOKEN);

        Gson gson = new Gson();
        String jsonStr = gson.toJson(Map.of("error", e.getErrorCode().getMsg()));
        // response.getWriter().write(jsonStr);
        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonStr);
        printWriter.close(); 
        
    }
    
}
