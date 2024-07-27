package org.zerock.mallapi.service.kafka;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductCreatedEvent {
    
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;    
    
    public ProductCreatedEvent() {

    }

    public ProductCreatedEvent(String productId, String title, BigDecimal price,Integer quantity) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }
}
