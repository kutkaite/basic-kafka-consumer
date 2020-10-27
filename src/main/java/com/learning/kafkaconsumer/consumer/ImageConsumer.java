package com.learning.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.kafkaconsumer.entity.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.net.http.HttpConnectTimeoutException;

@Service
public class ImageConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(ImageConsumer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "t_image", containerFactory = "imageRetryContainerFactory")
    public void consumeMessage(String message) throws JsonProcessingException, HttpConnectTimeoutException {
        Image image = objectMapper.readValue(message, Image.class);

        if (image.getType().equalsIgnoreCase("svg")) {
            throw new HttpConnectTimeoutException("API call failed");
        }

        LOG.info("Processing image: {}", image);
    }
}
