package org.zerock.mallapi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyDTO {
    
    private Long rno;

    private String text;

    private String replyer;

    private Long bno; // 게시글 번호

    private LocalDateTime regDate, modDate;
}
