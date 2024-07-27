package org.zerock.mallapi.service;

import org.springframework.stereotype.Service;
import org.zerock.mallapi.domain.Payment;
import org.zerock.mallapi.dto.KakaoPayResponseDTO;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public KakaoPayResponseDTO createKaKaoPayPayment(Payment payment) {
        
        throw new UnsupportedOperationException("Unimplemented method 'createKaKaoPayPayment'");
    }
    
}
