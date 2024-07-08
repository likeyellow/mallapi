package org.zerock.mallapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.mallapi.entity.UserIP;

public interface UserIpRepository extends JpaRepository<UserIP, Long> {
    // 추가적인 쿼리 메서드가 필요하다면 작성 가능
}
