package org.zerock.mallapi.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    
    private Long mno;

    private String title;

    @Builder.Default
    private List<MovieImageDTO> imageDTOList = new ArrayList<>();

    // 영화의 평균 평점
    private double avg;

    // 리뷰수 jpa의 count()
    private int reviewCnt;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
    
}
