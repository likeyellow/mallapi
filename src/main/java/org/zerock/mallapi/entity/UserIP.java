package org.zerock.mallapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@ToString
@Table(name = "tbl_ip")
public class UserIP {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ipAddress;
}
/*
 * Hibernate: 
    create table tbl_ip (
        id bigint not null auto_increment,
        ip_address varchar(255),
        primary key (id)
    ) engine=InnoDB
 */