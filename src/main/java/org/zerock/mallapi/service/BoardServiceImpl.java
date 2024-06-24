package org.zerock.mallapi.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.mallapi.dto.BoardDTO;
import org.zerock.mallapi.dto.PageRequestDTO2;
import org.zerock.mallapi.dto.PageResultDTO2;
import org.zerock.mallapi.entity.Board;
import org.zerock.mallapi.entity.Member2;
import org.zerock.mallapi.repository.BoardRepository;
import org.zerock.mallapi.repository.ReplyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {
    
    private final BoardRepository repository; // 자동 주입 final
    private final ReplyRepository replyRepository; 

    @Override
    public Long register(BoardDTO dto) {
        
        log.info(dto);

        Board board = dtoToEntity(dto);

        repository.save(board);

        return board.getBno();
    }


    @Override
    public PageResultDTO2<BoardDTO, Object[]> getList(PageRequestDTO2 pageRequestDTO) {
        
        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board)en[0], (Member2)en[1], (Long)en[2]));

        //Page<Object[]> result = repository.getBoardWithReplyCount(
        //    pageRequestDTO.getPageable(Sort.by("bno").descending()));
        
        Page<Object[]> result = repository.searchPage(
            pageRequestDTO.getType(), 
            pageRequestDTO.getKeyword(), 
            pageRequestDTO.getPageable(Sort.by("bno").descending()) );

        return new PageResultDTO2<>(result, fn);
    }


    @Override
    public BoardDTO get(Long bno) {
        
        Object result = repository.getBoardByBno(bno);

        Object[] arr = (Object[]) result;

        return entityToDTO((Board)arr[0], (Member2)arr[1], (Long)arr[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {   // 삭제 기능 구현, 트랜잭션 추가
        
        // 댓글부터 삭제
        replyRepository.deleteByBno(bno);

        repository.deleteById(bno);
    }

    @Transactional
    @Override
    public void modify(BoardDTO boardDTO) {
        
        Board board = repository.getOne(boardDTO.getBno());

        if(board != null) {

            board.changeTitle(boardDTO.getTitle());
            board.changeContent(boardDTO.getContent());

            repository.save(board);
        }
    }
}
