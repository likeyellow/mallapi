package org.zerock.mallapi.io;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="processed-events")
public class ProcessedEventEntity implements Serializable {
    
    private static final long serialVersionUID = 3687553269742697084L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false, unique=true)
    private String messageId;

    @Column(nullable = false)
    private String productId;

    public ProcessedEventEntity(String messageId, String productId) {
        this.messageId = messageId;
        this.productId = productId;
    }


}
