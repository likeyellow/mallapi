package org.zerock.mallapi.service;

import org.zerock.mallapi.dto.BoardDTO;
import org.zerock.mallapi.dto.PageRequestDTO2;
import org.zerock.mallapi.dto.PageResultDTO2;
import org.zerock.mallapi.entity.Board;
import org.zerock.mallapi.entity.Member2;

public interface BoardService {
    
    Long register(BoardDTO dto);

    PageResultDTO2<BoardDTO, Object[]> getList(PageRequestDTO2 pageRequestDTO); // 목록처리

    BoardDTO get(Long bno);

    void removeWithReplies(Long bno); // 삭제 기능

    void modify(BoardDTO boardDTO);

    default Board dtoToEntity(BoardDTO dto) {
        
        Member2 member = Member2.builder().email(dto.getWriterEmail()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        
                return board;        
    }

    // BoardService 인터페이스에 추가하는 entityToDTO()
    default BoardDTO entityToDTO(Board board,  Member2 member, Long replyCount) {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue()) // long으로 나오므로 int로 처리하도록
                .build();

        return boardDTO;        
    }
}
