package com.learning.kafkaconsumer.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.kafkaconsumer.entity.CarLocation;
import com.learning.kafkaconsumer.error.handler.GlobalErrorHandler;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<Object, Object> consumerFactory() {
        Map<String, Object> properties = kafkaProperties.buildConsumerProperties();

        properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "120000");
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean(name = "kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> filteredListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory());

        factory.setErrorHandler(new GlobalErrorHandler());
        return factory;
    }


    @Bean(name = "filteredLocationContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> filteredLocationContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory());
        factory.setRecordFilterStrategy(new RecordFilterStrategy<>() {

            final ObjectMapper objectMapper = new ObjectMapper();

            @Override
            public boolean filter(ConsumerRecord<Object, Object> consumerRecord) {
                try {
                    CarLocation carLocation = objectMapper.readValue(consumerRecord.value().toString(), CarLocation.class);
                    return carLocation.getDistance() <= 100;
                } catch (JsonProcessingException e) {
                    return false;
                }
            }
        });
        return factory;
    }
}
