package org.zerock.mallapi.service.kafka;

import org.zerock.mallapi.controller.kafka.CreateProductRestModal;

public interface ProductService2 {
    
    String createProduct2(CreateProductRestModal productRestModal) throws Exception;
}
