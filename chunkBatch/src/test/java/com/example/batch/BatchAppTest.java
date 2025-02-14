package com.example.batch;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BatchAppTest {

  @Test
  void contextLoads() {
    // Confirm that the Spring application context loads without errors
  }

  @Test
  void mainMethodTest() {
    // Arrange
    String[] args = {"--spring.batch.job.enabled=false"};

    // Act
    BatchApp.main(args);

    // Assert
    // No exceptions should occur
  }
}
