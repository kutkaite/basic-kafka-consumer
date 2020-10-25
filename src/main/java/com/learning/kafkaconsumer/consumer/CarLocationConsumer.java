package com.learning.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.kafkaconsumer.entity.CarLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CarLocationConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(CarLocationConsumer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "t_location", groupId = "cg-all-location")
    private void listenAll(String message) throws JsonProcessingException {
        CarLocation carLocation = objectMapper.readValue(message, CarLocation.class);
        LOG.info("listenAll: {}", carLocation);
    }

    @KafkaListener(topics = "t_location", groupId = "cg-filtered-location", containerFactory = "filteredLocationContainerFactory")
    private void listenFiltered(String message) throws JsonProcessingException {
        CarLocation carLocation = objectMapper.readValue(message, CarLocation.class);

        LOG.info("listenFiltered: {}", carLocation);
    }
}
