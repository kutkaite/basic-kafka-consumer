package com.learning.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.kafkaconsumer.entity.FoodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FoodOrderConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(FoodOrderConsumer.class);
    private static final int MAX_AMOUNT_ORDER = 7;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "t_food_order", errorHandler = "foodOrderErrorHandler")
    public void consume(String message) throws JsonProcessingException {
        FoodOrder foodOrder = objectMapper.readValue(message, FoodOrder.class);

        if (foodOrder.getAmount() > MAX_AMOUNT_ORDER) {
            throw new IllegalArgumentException("Food order amount should be less than " + MAX_AMOUNT_ORDER);
        }

        LOG.info("Food order is valid: {}", foodOrder);
    }
}
