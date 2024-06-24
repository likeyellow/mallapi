package org.zerock.mallapi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    
    //review num
    private Long reviewnum;

    //Movie mno
    private Long mno;

    // Memeber id
    private Long mid;
    // Member nickname
    private String nickname;
    // Member email
    private String email;

    private int grade;

    private String text;

    private LocalDateTime regDate, modDate;
}
