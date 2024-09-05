package org.zerock.mallapi.service.kafka;

import org.zerock.mallapi.controller.kafka.CreateProductRestModel;

public interface ProductService2 {
    
    String createProduct2(CreateProductRestModel productRestModel) throws Exception;
}
