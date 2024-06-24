package org.zerock.mallapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mallapi.entity.Board;
import org.zerock.mallapi.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // Board 삭제시에 댓글들 삭제
    @Modifying
    @Query("delete from Reply r where r.board.bno = :bno ")
    void deleteByBno(Long bno);
    
    // 게시물로 댓글 목록 가져오기
    List<Reply> getRepliesByBoardOrderByRno(Board board);

}
