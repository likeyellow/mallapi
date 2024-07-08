package org.zerock.mallapi.service;

import org.springframework.stereotype.Service;
import org.zerock.mallapi.dto.UserIPDTO;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class UserIPServiceImpl implements UserIPService {

    @Override
    public Long ipRegister(UserIPDTO userIPDTO) {
        
        log.info("ipRegister");
        
        return null;
    }
    
}
