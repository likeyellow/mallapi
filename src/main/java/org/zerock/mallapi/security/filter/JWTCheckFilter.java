package org.zerock.mallapi.security.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.zerock.mallapi.domain.ErrorCode;
import org.zerock.mallapi.dto.MemberDTO;
import org.zerock.mallapi.util.JWTUtil;
import org.zerock.mallapi.util.MemberException;

import com.google.gson.Gson;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {
    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        
        // Preflight요청은 체크하지 않음
        if(request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String path = request.getRequestURI();
        log.info("check uri................." + path);

        // api/member/ 경로의 호출은 체크하지 않음
        if(path.startsWith("/api/member/")) {
            return true;
        }

        // 이미지 조회 경로를 체크하지 않는다면
        // if(path.startsWith("/sample/")) {
        //     return true;
        // }

        if(path.startsWith("/uploadAjax/")) {
            return true;
        }
        if(path.startsWith("/jquery/")) {
            return true;
        }
        // api/member/ 경로의 호출은 체크하지 않음
        // if(path.startsWith("/api/member/kakao")) {
        //     return true;
        // }

        // 이미지 조회 경로는 체크하지 않는다면
        if(path.startsWith("/api/products/view/")) {
            return true;
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                FilterChain filterChain)
            throws ServletException, IOException {
        
        log.info("---------------JWTCheckFilter----------------");
        
        String authHeaderStr = request.getHeader("Authorization");

        try {
            // Bearer accesstoken...
            String accessToken = authHeaderStr.substring(7);
            Map<String, Object> claims = JWTUtil.validateToken(accessToken);

            log.info("JWT claims: " + claims);

            // filterChain.doFilter(request, response); // 이하 추가

            String email = (String) claims.get("email");
            String pw = (String) claims.get("pw");
            String nickname = (String) claims.get("nickname");
            Boolean social = (Boolean) claims.get("social");
            List<String> roleNames = (List<String>) claims.get("roleNames");

            MemberDTO memberDTO = new MemberDTO(email, pw, nickname, social.booleanValue(), roleNames);

            log.info("----------------------------------");
            log.info(memberDTO);
            log.info(memberDTO.getAuthorities());

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(memberDTO, pw, memberDTO.getAuthorities());
            
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);    

            filterChain.doFilter(request, response);

        // JWTCheckFilter에서 JWT 토큰 검증이 실패한 경우 AuthenticationException 에러가 발생함
        // 따라서 해당 에러 종류를 명시적으로 표시하기 위해 e의 타입을 AuthenticationException 으로 변경함    
        } catch(AuthenticationException e) { 

            log.error("JWT Check Error.................");
            log.error(e.getMessage());

            // CustomAuthenticationEntryPoint 에서 해당 에러를 처리하기 위해 주석처리 함
            // Gson gson = new Gson();
            // String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));

            // response.setContentType("application/json");
            // PrintWriter printWriter = response.getWriter();
            // printWriter.println(msg);
            // printWriter.close();

            
            // CustomAuthenticationEntryPoint 에서 해당 에러를 처리하도록 수정
            throw e;
        }
    }
}
