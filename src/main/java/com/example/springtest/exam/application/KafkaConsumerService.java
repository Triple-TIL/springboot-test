package com.example.springtest.exam.application;

import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

  public void process(String message) {
    System.out.println("Processing.... " + message);
  }
}
