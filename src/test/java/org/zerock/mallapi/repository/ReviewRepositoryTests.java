package org.zerock.mallapi.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.zerock.mallapi.entity.Member3;
import org.zerock.mallapi.entity.Movie;
import org.zerock.mallapi.entity.Review;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ReviewRepositoryTests {
    
    @Autowired
    private Member3Repository member3Repository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews() {

        // 200개의 리뷰를 등록
        IntStream.rangeClosed(1, 200).forEach(i -> {

            // 영화 번호
            Long mno = (long)(Math.random() * 100) + 1;

            // 리뷰어 번호
            Long mid = ((long)(Math.random() * 100) +1);
            Member3 member = Member3.builder().mid(mid).build();

            Review movieReview = Review.builder()
                    .member(member)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int)(Math.random()*5) +1)
                    .text("잼난다~~~~" +i)
                    .build();
            reviewRepository.save(movieReview);        
        });
    }

    @Test
    public void testGetMovieReviews() {

        Movie movie = Movie.builder().mno(92L).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(movieReview -> {

            System.out.println(movieReview.getReviewnum());
            System.out.println("\t"+movieReview.getGrade());
            System.out.println("\t"+movieReview.getText());
            System.out.println("\t"+movieReview.getMember().getEmail());
            System.out.println("----------------------------");
        });
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteMember() {

        Long mid = 5L; // Member3의 mid

        Member3 member = Member3.builder().mid(mid).build();

        // 기존
        // member3Repository.deleteById(mid);
        // reviewRepository.deleteByMember(member);

        // 순서 주의
        reviewRepository.deleteByMember(member);
        member3Repository.deleteById(mid);
    }
}
