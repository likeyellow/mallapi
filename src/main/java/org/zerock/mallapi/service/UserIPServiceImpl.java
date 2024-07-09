package org.zerock.mallapi.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.mallapi.dto.UserIPDTO;
import org.zerock.mallapi.entity.UserIP;
import org.zerock.mallapi.repository.UserIpRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class UserIPServiceImpl implements UserIPService {

    // 자동주입 대상은 final로
    private final ModelMapper modelMapper; // ModelMapper를 주입받음

    private final UserIpRepository userIpRepository;

    @Override
    public Long ipRegister(UserIPDTO userIPDTO) {
        
        log.info("ipRegister");

        UserIP userIP = modelMapper.map(userIPDTO, UserIP.class);
        UserIP savedUserIP = userIpRepository.save(userIP);

        return savedUserIP.getId();

    }
    
}
