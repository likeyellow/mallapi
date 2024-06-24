package org.zerock.mallapi.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.entity.Member3;

@SpringBootTest
public class Member3RepositoryTests {
    
    @Autowired
    private Member3Repository member3Repository;

    @Test
    public void insertMebers() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            
            Member3 member = Member3.builder()
                    .email("r"+i + "@test.com")
                    .pw("1111")
                    .nickname("reviewer"+i).build();

            member3Repository.save(member);        
        });
    }
}
