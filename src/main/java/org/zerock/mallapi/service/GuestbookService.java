package org.zerock.mallapi.service;

import org.zerock.mallapi.dto.GuestbookDTO;
import org.zerock.mallapi.dto.PageRequestDTO2;
import org.zerock.mallapi.dto.PageResultDTO2;
import org.zerock.mallapi.entity.Guestbook;

public interface GuestbookService {
    
    GuestbookDTO read(Long gno);

    Long register(GuestbookDTO dto);

    void remove(Long gno);

    void modify(GuestbookDTO dto);

    PageResultDTO2<GuestbookDTO, Guestbook> getList(PageRequestDTO2 requestDTO);

    default GuestbookDTO entityToDto(Guestbook entity) { // 엔티티 객체를 DTO 객체로 변환
        GuestbookDTO dto = GuestbookDTO.builder()
                    .gno(entity.getGno())
                    .title(entity.getTitle())
                    .content(entity.getContent())
                    .writer(entity.getWriter())
                    .regDate(entity.getRegDate())
                    .modDate(entity.getModDate())
                    .build();
        return dto;            
    }

    default Guestbook dtoToEntity(GuestbookDTO dto) { // DTO 객체를 엔티티 객체로 변환
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();     
        return entity;
    }
}
