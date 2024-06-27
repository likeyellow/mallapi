package org.zerock.mallapi.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.beans.propertyeditors.URLEditor;
import org.springframework.security.access.event.AuthenticationCredentialsNotFoundEvent;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class APILoginFailHandler implements AuthenticationFailureHandler {

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


        //String errorMessage = null;
        int errorCode = 0;

        if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
            //errorMessage = "Username과 Password가 맞지 않습니다. 다시 확인해 주십시오.";
            errorCode = 500;
        } else if(exception instanceof DisabledException) {
            //errorMessage = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.";
            errorCode = 501;
        } else if(exception instanceof InternalAuthenticationServiceException) {
            //errorMessage = "내부적으로 발생한 시스템 문제로 인해 요청을 처리할 수 없습니다. 관리자에게 문의하세요.";
            errorCode = 502;
        } else if(exception instanceof UsernameNotFoundException) {
            //errorMessage = "계정이 존재하지 않습니다. 회원가입 진행 후 로그인 해주세요.";
            errorCode = 503;
        } else if(exception instanceof AuthenticationCredentialsNotFoundException) {
            //errorMessage = "인증 요청이 거부되었습니다. 관리자에게 문의하세요.";
            errorCode = 504;
        } else {
            //errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요.";
            errorCode = 404;
        }

        Gson gson = new Gson();

        String jsonStr = gson.toJson(Map.of("error", errorCode));
 
        response.setContentType("application/json; charset=UTF-8");

        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonStr);
        printWriter.close();       
    }
}
