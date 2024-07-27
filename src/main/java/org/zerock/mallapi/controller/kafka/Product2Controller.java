package org.zerock.mallapi.controller.kafka;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.mallapi.service.kafka.ProductService2;

@RestController
@RequestMapping("/api/products2") //http://localhost:<port>/api/products2
public class Product2Controller {
    
    ProductService2 productService2;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public Product2Controller(ProductService2 productService2) {
        this.productService2 = productService2;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductRestModal product) {

        String productId;
        try {
            productId = productService2.createProduct2(product);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage(new Date(), e.getMessage(), "/api/products2"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}
