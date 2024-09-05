package org.zerock.mallapi.dto;
// 결제 요청할 때 사용
import lombok.Data;

@Data
public class KakaoPayResponseDTO {
    private String tid; // 결제 고유 번호(20자)
    private String next_redirect_pc_url; // 요청한 클라이언트가 PC웹일 경우
                                        // 카카오톡으로 결제요청메시지(TMS)를 보내기 위한 
                                        // 사용자 정보 입력화면 Redirect URL
                                        // 카카오 결제 창이 나오는 url        
    private String created_at; // 결제 준비 요청 시간
    private String partner_order_id; // 가맹점 주문 번호(최대 100자)
}
