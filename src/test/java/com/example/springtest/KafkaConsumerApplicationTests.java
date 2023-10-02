package com.example.springtest;

import com.example.springtest.exam.application.KafkaConsumerService;
import com.example.springtest.exam.application.KafkaProducerService;
import com.example.springtest.exam.integration.IntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

@Order(0)
public class KafkaConsumerApplicationTests extends IntegrationTest {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @Test
    void kafkaSendAndConsumerTest() {
        String topic = "test-topic";
        String expectValue = "expect-value";

        kafkaProducerService.send(topic, expectValue);

        var stringCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(kafkaConsumerService, Mockito.timeout(5000).times(1))
                .process(stringCaptor.capture());

        Assertions.assertEquals(expectValue, stringCaptor.getValue());
    }

}
