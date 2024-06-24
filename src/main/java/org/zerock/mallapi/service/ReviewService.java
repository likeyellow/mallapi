package org.zerock.mallapi.service;

import java.util.List;

import org.zerock.mallapi.dto.ReviewDTO;
import org.zerock.mallapi.entity.Member3;
import org.zerock.mallapi.entity.Movie;
import org.zerock.mallapi.entity.Review;

public interface ReviewService {
    
        // 영화의 모든 영호리뷰를 가져온다
    List<ReviewDTO> getListOfMovie(Long mno);

    // 영화 리뷰를 추가
    Long register(ReviewDTO moviReviewDTO);

    // 특정한 영화리뷰 수정
    void modify(ReviewDTO movieReviewDTO);

    // 영화 리뷰 삭제
    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDTO movieReviewDTO) {
        
        Review movieReview = Review.builder()
            .reviewnum(movieReviewDTO.getReviewnum())
            .movie(Movie.builder().mno(movieReviewDTO.getMno()).build())
            .member(Member3.builder().mid(movieReviewDTO.getMid()).build())
            .grade(movieReviewDTO.getGrade())
            .text(movieReviewDTO.getText())
            .build();

        return movieReview;    
    }

    default ReviewDTO entityToDto(Review movieReview) {

        ReviewDTO movieReviewDTO = ReviewDTO.builder()
                    .reviewnum(movieReview.getReviewnum())
                    .mno(movieReview.getMovie().getMno())
                    .mid(movieReview.getMember().getMid())
                    .nickname(movieReview.getMember().getNickname())
                    .email(movieReview.getMember().getEmail())
                    .grade(movieReview.getGrade())
                    .text(movieReview.getText())
                    .regDate(movieReview.getRegDate())
                    .modDate(movieReview.getModDate())
                    .build();
        
        return movieReviewDTO;            
    }
}
