package com.example.batch;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(
    properties = {
      "spring.batch.job.enabled=false",
      "spring.datasource.mysqlmain.url=jdbc:mysql://localhost:3306/sampledb",
      "spring.datasource.mysqlmain.username=sampleuser",
      "spring.datasource.mysqlmain.password=samplepassword"
    })
class DbAndCsvBatchAppTest {

  @Test
  void testMainMethod() {
    // Arrange
    String[] args = {"--spring.batch.job.name=DB_TO_CSV", "--spring.profiles.active=local"};
    System.setProperty("sun.java.command", String.join(" ", args));

    // Act
    DbAndCsvBatchApp.main(args);

    // Assert
    // Main method execution doesn't throw exceptions
  }
}
