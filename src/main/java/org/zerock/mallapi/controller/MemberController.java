package org.zerock.mallapi.controller;

import java.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.mallapi.dto.MemberDTO;
import org.zerock.mallapi.dto.UserIPDTO;
import org.zerock.mallapi.service.MemberService;
import org.zerock.mallapi.service.UserIPService;
import org.zerock.mallapi.util.GetIpFromHeader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
    
    private final UserIPService userIPService;

    private final GetIpFromHeader getIpUtil;

    private final MemberService memberService;

    @GetMapping("/login") // 로그인 페이지 URL
    public String getLoginForm() {
        
        log.info("로그인페이지");

        // 로그인 페이지에 접근한 사용자의 IP주소를 가져오기
        String clientIp = getIpUtil.getIpFromHeader();
        log.info("아이피: " + clientIp);

        // 로그인 페이지에 접근한 사용자의 IP주소를 DB에 저장
        UserIPDTO userIPDTO = new UserIPDTO();
        userIPDTO.setIpAddress(clientIp);
        userIPDTO.setAccessTime(LocalDateTime.now());

        userIPService.ipRegister(userIPDTO); 

        return "loginPage"; // 로그인 페이지 뷰 이름 반환
    }

    // 회원가입 페이지 반환
    @GetMapping("/register")
    public String getRegisterForm(Model model) {

        log.info("회원가입페이지");

        //model.addAttribute("member", new MemberDTO());
        model.addAttribute("model", model);
        return "register"; // React LoginPage component
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String registerMember(@ModelAttribute MemberDTO memberDTO, Model model) {
        boolean isRegistered = memberService.registerMember(memberDTO);

        if(isRegistered) {
            return "redirect:/member/register/success";
        } else {
            model.addAttribute("error", "회원가입에 실패하였습니다. 다시 시도해주세요.");
            return "registerPage";
        }
    }

    // 회원가입 성공 페이지 반환
    @GetMapping("/register/success")
    public String getRegisterSuccess() {
        return "registerSuccessPage";
    }


    // SSR 테스트용
    @GetMapping("/hello")
    public String hello(Model model) {

        log.info("SSR테스트");

        model.addAttribute("message", "Hello, Spring Boot");
        return "HelloPage";
    }

}
