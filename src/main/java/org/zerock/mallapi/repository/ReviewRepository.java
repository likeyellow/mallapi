package org.zerock.mallapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mallapi.entity.Member3;
import org.zerock.mallapi.entity.Movie;
import org.zerock.mallapi.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

    @Modifying
    @Query("delete from Review mr where mr.member = :member")
    void deleteByMember(Member3 member);
}
