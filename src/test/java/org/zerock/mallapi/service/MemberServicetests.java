package org.zerock.mallapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServicetests {
    
    @Autowired
    private MemberService memberService;

    // [ mockMvc ]를 이용하여 테스트
    // 1. 유효하지 않은 토큰 값을 헤더에 넣어서 리퀘스트 보내기
    // 2. 유효하지만 해당 리퀘스트에 대한 권한이 없는 토큰을 보내기
    //  ㄴ 인증을 먼저 해서 토큰을 얻어야 가능한 테스트임

    

}
