package org.zerock.mallapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.zerock.mallapi.dto.ReplyDTO;
import org.zerock.mallapi.entity.Board;
import org.zerock.mallapi.entity.Reply;
import org.zerock.mallapi.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    
    private final ReplyRepository replyRepository;
    
    @Override
    public Long register(ReplyDTO replyDTO) {
        
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);

        return reply.getRno();
    }

    @Override
    public List<ReplyDTO> getList(Long bno) {
        
        List<Reply> result = replyRepository
                    .getRepliesByBoardOrderByRno(Board.builder().bno(bno).build());
        
        return result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());            
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        
        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno) {
        
        replyRepository.deleteById(rno);
    }

    
    
}
