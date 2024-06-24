package org.zerock.mallapi.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.entity.Member2;

@SpringBootTest
public class MemberRepositoryTests {
    
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void inserMembers() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            
            Member2 member = Member2.builder()
                .email("user"+i + "@aaa.com")
                .password("1111")
                .name("USER"+i)
                .build();
            
                memberRepository.save(member);
        });
    }
}
