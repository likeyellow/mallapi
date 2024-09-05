package org.zerock.mallapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_payment")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY) // Cart와의 관계 추가
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private String tid; // 카카오페이에서 발급된 거래 ID
    private Long orderId; // 주문 ID
    //private Long memberId; // 회원 ID
    //private String itemName; // 상품명
    private int quantity; // 수량
    private int totalAmount; // 총 결제 금액
    private String status; // 결제 상태(PENDING, COMPLETED, CANCELLED 등)
    private int tax_free; // 비과세 금액
    private int vat; // 부과세 금액
    private int point; // 사용한 포인트
    private int discount; // 할인 금액
}
