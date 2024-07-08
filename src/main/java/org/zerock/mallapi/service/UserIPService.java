package org.zerock.mallapi.service;

import org.zerock.mallapi.dto.UserIPDTO;

public interface UserIPService {
    
    Long ipRegister(UserIPDTO userIPDTO);
}
