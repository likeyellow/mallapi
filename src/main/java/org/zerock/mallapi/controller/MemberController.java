package org.zerock.mallapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.mallapi.dto.UserIPDTO;
import org.zerock.mallapi.service.UserIPService;
import org.zerock.mallapi.util.GetIpFromHeader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Controller
public class MemberController {
    
    private final UserIPService userIPService;

    private final GetIpFromHeader getIpUtil;


    @GetMapping("/member/login") // 로그인 페이지 URL
    public String getLoginForm() {
        
        log.info("로그인페이지");

        // 로그인 페이지에 접근한 사용자의 IP주소를 가져오기
        String clientIp = getIpUtil.getIpFromHeader();
        log.info("아이피: " + clientIp);

        // 로그인 페이지에 접근한 사용자의 IP주소를 DB에 저장
        UserIPDTO userIPDTO = new UserIPDTO();
        userIPDTO.setIpAddress(clientIp);

        userIPService.ipRegister(userIPDTO); 

        return "loginPage"; // 로그인 페이지 뷰 이름 반환
    }
}
