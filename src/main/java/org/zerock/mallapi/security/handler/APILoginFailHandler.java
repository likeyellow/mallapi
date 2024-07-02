package org.zerock.mallapi.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.print.attribute.standard.Media;

import org.springframework.beans.propertyeditors.URLEditor;
import org.springframework.http.MediaType;
import org.springframework.security.access.event.AuthenticationCredentialsNotFoundEvent;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.zerock.mallapi.domain.ErrorCode;
import org.zerock.mallapi.util.MemberException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class APILoginFailHandler implements AuthenticationFailureHandler {
// 인증이 실패했을 때 호출되는 AuthenticationFailurHandler 호출됨(ex: 비번 오류)
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        
        log.info("Login fail............" + exception);
        
        // Gson gson = new Gson();
        
        // String jsonStr = gson.toJson(Map.of("error", "ERROR_LOGIN"));

        // response.setContentType("application/json");

        // PrintWriter printWriter = response.getWriter();
        // printWriter.println(jsonStr);
        // printWriter.close();

        // 에러코드와 에러 메시지를 domain/ErrorCode 에서 관리하는 것으로 변경
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        MemberException e = new MemberException(ErrorCode.AUTHENTICATION_FAILED);   

        Gson gson = new Gson();
        String jsonStr = gson.toJson(Map.of("error", e.getErrorCode().getMsg()));

        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonStr);
        printWriter.close();       
    }
}
