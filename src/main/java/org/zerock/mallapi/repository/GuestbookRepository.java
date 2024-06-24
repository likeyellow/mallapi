package org.zerock.mallapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.mallapi.entity.Guestbook;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long>, 
            QuerydslPredicateExecutor<Guestbook> {
    
}
