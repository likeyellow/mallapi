package org.zerock.mallapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.dto.GuestbookDTO;
import org.zerock.mallapi.dto.PageRequestDTO2;
import org.zerock.mallapi.dto.PageResultDTO2;
import org.zerock.mallapi.entity.Guestbook;

@SpringBootTest
public class GuestbookServiceTests {
    
    @Autowired
    private GuestbookService service;

    @Test
    public void testRegister() {

        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                        .title("Sample Title....")
                        .content("Sample Content....")
                        .writer("user0")
                        .build();

        System.out.println(service.register(guestbookDTO));
    }

    @Test
    public void testList() {

        PageRequestDTO2 pageRequestDTO2 = PageRequestDTO2.builder().page(1).
                                size(10).build();

        PageResultDTO2<GuestbookDTO, Guestbook> resultDTO = service.
                            getList(pageRequestDTO2);
        
        // for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
        //     System.out.println(guestbookDTO);
        // }
        
        System.out.println("PREV: "+resultDTO.isPrev());
        System.out.println("NEXT: "+resultDTO.isNext());
        System.out.println("TOTAL: "+resultDTO.getTotalPage());
        System.out.println("------------------------------------");
        for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println(guestbookDTO);
        }

        System.out.println("=====================================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }

    @Test
    public void testSearch() {

        PageRequestDTO2 pageRequestDTO = PageRequestDTO2.builder()
            .page(1)
            .size(10)
            .type("tc") // 검색 조건 t, c, w, tc, tcw ..
            .keyword("한글") // 검색 키워드
            .build();

            PageResultDTO2<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

            System.out.println("Prev: " + resultDTO.isPrev());
            System.out.println("Next: "+resultDTO.isNext());
            System.out.println("Total: "+resultDTO.getTotalPage());

            System.out.println("-------------------------");
            for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
                System.out.println(guestbookDTO);
            }

            System.out.println("========================");
            resultDTO.getPageList().forEach(i -> System.out.println(i));
    }
}
