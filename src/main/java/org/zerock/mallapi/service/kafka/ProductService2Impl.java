package org.zerock.mallapi.service.kafka;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.zerock.mallapi.controller.kafka.CreateProductRestModal;

@Service
public class ProductService2Impl implements ProductService2 {

    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    public ProductService2Impl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct2(CreateProductRestModal productRestModal) throws Exception {
        
        String productId = UUID.randomUUID().toString();

        // TODO: Persist Product Details into database table before publishing an Event

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId,
                    productRestModal.getTitle(), productRestModal.getPrice(),
                    productRestModal.getQuantity());

        LOGGER.info("Before publishing a ProductCreatedEvent");

        SendResult<String, ProductCreatedEvent> result = 
            kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent).get();
        LOGGER.info("Partition: " + result.getRecordMetadata().partition());
        LOGGER.info("Topic: " + result.getRecordMetadata().topic());
        LOGGER.info("Offset: " + result.getRecordMetadata().offset());
        // CompletableFuture <SendResult<String, ProductCreatedEvent>> future = 
        //     kafkaTemplate.send("product-created-events-topic",productId, productCreatedEvent);
        
        // future.whenComplete((result, exception) -> {

        //     if(exception != null) {
        //         LOGGER.error("Failed to send message: " + exception.getMessage());
        //     } else {
        //         LOGGER.info("Message sent successfully: " + result.getRecordMetadata());
        //     }
        // });

        // //future.join();  // 동기식으로 작동함, 응답이 올때까지 이 줄에서 기다림 

        LOGGER.info("***** Return product id");
        return productId;
    }
    
}
