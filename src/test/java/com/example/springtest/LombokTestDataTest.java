package com.example.springtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LombokTestDataTest {

  @Test
  void testDataTest() {
    TestData testData = new TestData();

    testData.setName("sss");

    Assertions.assertEquals("sss", testData.getName());
  }
}
