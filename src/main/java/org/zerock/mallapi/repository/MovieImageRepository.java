package org.zerock.mallapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.mallapi.entity.MovieImage;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
    
}
