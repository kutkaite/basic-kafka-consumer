package com.learning.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.kafkaconsumer.entity.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InvoiceConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(InvoiceConsumer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "t_invoice", containerFactory = "invoiceDltContainerFactory")
    public void consumeMessage(String message) throws JsonProcessingException {
        Invoice invoice = objectMapper.readValue(message, Invoice.class);

        if (invoice.getAmount() < 1) {
            throw new IllegalArgumentException("Invalid amount: " + invoice.getAmount() + " for invoice: " + invoice.getNumber());
        }

        LOG.info("Processing invoice: {}", invoice);
    }
}
