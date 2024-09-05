package org.zerock.mallapi.controller.kafka;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRestModel {

    private String title;
    private BigDecimal price;
    private Integer quantity;
}
