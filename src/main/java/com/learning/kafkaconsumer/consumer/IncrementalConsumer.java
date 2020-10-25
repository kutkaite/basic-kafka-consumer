package com.learning.kafkaconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class IncrementalConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(IncrementalConsumer.class);

    @KafkaListener(topics = "t_incremental")
    public void consume(String message){
        LOG.info("Consuming : {}", message);
    }
}
