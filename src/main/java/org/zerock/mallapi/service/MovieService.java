package org.zerock.mallapi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.zerock.mallapi.dto.MovieDTO;
import org.zerock.mallapi.dto.MovieImageDTO;
import org.zerock.mallapi.dto.PageRequestDTO2;
import org.zerock.mallapi.dto.PageResultDTO2;
import org.zerock.mallapi.entity.Movie;
import org.zerock.mallapi.entity.MovieImage;

public interface MovieService {
    
    Long register(MovieDTO movieDTO);
    
    PageResultDTO2<MovieDTO, Object[]> getList(PageRequestDTO2 requestDTO); // 목록처리

    default MovieDTO entitiesToDTO(Movie movie, List<MovieImage> movieImages, Double avg, Long reviewCnt) {
        MovieDTO movieDTO = MovieDTO.builder()
            .mno(movie.getMno())
            .title(movie.getTitle())
            .regDate(movie.getRegDate())
            .modDate(movie.getModDate())
            .build();

        List<MovieImageDTO> movieImageDTOList = movieImages.stream().
            map(movieImage -> {
                return MovieImageDTO.builder()
                            .imgName(movieImage.getImgName())
                            .path(movieImage.getPath())
                            .uuid(movieImage.getUuid())
                            .build();
            }).collect(Collectors.toList());
            
            movieDTO.setImageDTOList(movieImageDTOList);
            movieDTO.setAvg(avg);
            movieDTO.setReviewCnt(reviewCnt.intValue());

            return movieDTO;
    }
    default Map<String, Object> dtoToEntity(MovieDTO movieDTO) { // Map타입으로 반환

        Map<String, Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder()
                .mno(movieDTO.getMno())
                .title(movieDTO.getTitle())
                .build();

        entityMap.put("movie", movie);
        
        List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();

        // MovieImageDTO 처리
        if(imageDTOList != null && imageDTOList.size() > 0) {
            List<MovieImage> movieImageList = imageDTOList.stream().
                map(movieImageDTO -> {

                    MovieImage movieImage = MovieImage.builder()
                            .imgName(movieImageDTO.getImgName())
                            .path(movieImageDTO.getPath())
                            .uuid(movieImageDTO.getUuid())
                            .movie(movie)
                            .build();
                    return movieImage;        
                }).collect(Collectors.toList());
                
                entityMap.put("imgList", movieImageList);
        }
        return entityMap;
    }

    MovieDTO getMovie(Long mno);
}
