package org.zerock.mallapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.dto.BoardDTO;
import org.zerock.mallapi.dto.PageRequestDTO2;
import org.zerock.mallapi.dto.PageResultDTO2;

@SpringBootTest
public class BoardServiceTests {
    
    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {

        BoardDTO dto = BoardDTO.builder()
                .title("Test")
                .content("Test....")
                .writerEmail("user55@aaa.com") // 현재 데이터베이스에 존재하는 회원 이메일
                .build();

        Long bno = boardService.register(dto);        
    }

    @Test
    public void testList() {

        PageRequestDTO2 pageRequestDTO = new PageRequestDTO2();

        PageResultDTO2<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

        for(BoardDTO boardDTO : result.getDtoList()) {
            System.out.println(boardDTO);
        }
    }

    @Test
    public void testGet() {

        Long bno = 100L;

        BoardDTO boardDTO = boardService.get(bno);

        System.out.println(boardDTO);
    }

    @Test
    public void testRemove() {

        Long bno = 1L;

        boardService.removeWithReplies(bno);
    }

    @Test
    public void testModify() {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(202L)
                .title("제목 변경합니다.")
                .content("내용 변경합니다.")
                .build();

        boardService.modify(boardDTO);        
    }
}
