package org.zerock.mallapi.service;

import org.zerock.mallapi.domain.Payment;
import org.zerock.mallapi.dto.KakaoPayResponseDTO;

public interface PaymentService {
    
    KakaoPayResponseDTO createKaKaoPayPayment(Payment payment);
}
