package com.learning.kafkaconsumer.error.handler;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ConsumerAwareErrorHandler;

public class GlobalErrorHandler implements ConsumerAwareErrorHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalErrorHandler.class);

    @Override
    public void handle(Exception thrownException, ConsumerRecord<?, ?> data, Consumer<?, ?> consumer) {
        LOG.warn("Global error handler for message: {}", data.value().toString());
    }
}
