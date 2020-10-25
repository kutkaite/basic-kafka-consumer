package com.learning.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.kafkaconsumer.entity.Commodity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class CommodityNotificationConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final static Logger LOG = LoggerFactory.getLogger(CommodityNotificationConsumer.class);

    @KafkaListener(topics = "t_commodity", groupId = "cg-notification")
    public void consume(String message) throws JsonProcessingException, InterruptedException {
        Commodity commodity = objectMapper.readValue(message, Commodity.class);

        Thread.sleep(ThreadLocalRandom.current().nextLong(500, 1000));

        LOG.info("Notification logic for: {}", commodity);
    }
}
