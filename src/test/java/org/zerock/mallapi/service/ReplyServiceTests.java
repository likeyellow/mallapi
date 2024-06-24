package org.zerock.mallapi.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.dto.ReplyDTO;

@SpringBootTest
public class ReplyServiceTests {
    
    @Autowired
    private ReplyService service;

    @Test
    public void testGetList() {

        Long bno = 100L; // 데이터베이스에 존재하는 번호

        List<ReplyDTO> replyDTOList = service.getList(bno);

        replyDTOList.forEach(replyDTO -> System.out.println(replyDTO));
    }
}
