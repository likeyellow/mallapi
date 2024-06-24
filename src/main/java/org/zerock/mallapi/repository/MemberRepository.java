package org.zerock.mallapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.mallapi.entity.Member2;


public interface MemberRepository extends JpaRepository<Member2, String> {
    
}
