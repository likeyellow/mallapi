package org.zerock.mallapi.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.zerock.mallapi.domain.ErrorCode;
import org.zerock.mallapi.util.MemberException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        
        // Gson gson = new Gson();
        
        // String jsonStr = gson.toJson(Map.of("error", "ERROR_ACCESSDENIED"));
        
        // response.setContentType("application/json");
        // response.setStatus(HttpStatus.FORBIDDEN.value());
        // PrintWriter printWriter = response.getWriter();
        // printWriter.println(jsonStr);
        // printWriter.close();        

        log.error("AccessDenied error: {}", accessDeniedException.getMessage());

        // 에러코드와 에러 메시지를 domain/ErrorCode 에서 관리하는 것으로 변경
        MemberException e = new MemberException(ErrorCode.ACCESS_DENIED);

        response.setStatus(e.getErrorCode().getStatus());

        Gson gson = new Gson();
        String jsonStr = gson.toJson(Map.of("error", e.getErrorCode().getMsg()));

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonStr);
        printWriter.close();
    }

    
    
}
