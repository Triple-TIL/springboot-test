package com.example.springtest.exam.integration;

import com.example.springtest.exam.application.S3Service;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

public class S3ServiceTest extends IntegrationTest {

  @Autowired private S3Service s3Service;

  @Test
  void s3PutAndGetTest() throws Exception {
    var bucket = "test-bucket";
    var key = "sampleObject.txt";
    var sampleFile = new ClassPathResource("static/sample.txt").getFile();

    s3Service.putFile(bucket, key, sampleFile);

    var resultFile = s3Service.getFile(bucket, key);

    List<String> sampleFileLines = FileUtils.readLines(sampleFile);
    List<String> resultFileLines = FileUtils.readLines(resultFile);

    Assertions.assertIterableEquals(sampleFileLines, resultFileLines);
  }
}
