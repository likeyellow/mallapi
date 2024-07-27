package org.zerock.mallapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.mallapi.domain.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    
    @Query("SELECT DISTINCT p " +
           "FROM Payment p " +
           "JOIN FETCH p.cart c " +
           "JOIN FETCH c.owner m " +
           "JOIN FETCH c.payments ci " +  // Cart의 payments 속성 사용
           "JOIN FETCH ci.product " +
           "WHERE m.id = :memberId")
    List<Payment> findAllPaymentsByMemberId(@Param("memberId") Long memberId);
}
