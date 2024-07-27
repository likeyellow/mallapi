package org.zerock.mallapi.controller.kafka;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRestModal {

    private String title;
    private BigDecimal price;
    private Integer quantity;
}
