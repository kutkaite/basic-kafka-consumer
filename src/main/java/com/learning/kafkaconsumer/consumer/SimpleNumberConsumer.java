package com.learning.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.kafkaconsumer.entity.SimpleNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SimpleNumberConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleNumberConsumer.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "t_simple_number")
    public void consume(String message) throws JsonProcessingException {
        SimpleNumber simpleNumber = objectMapper.readValue(message, SimpleNumber.class);

        if (simpleNumber.getNumber() % 2 != 0) {
            throw new IllegalArgumentException("Odd number not allowed");
        }

        LOG.info("Valid number : {} ", simpleNumber);
    }
}
