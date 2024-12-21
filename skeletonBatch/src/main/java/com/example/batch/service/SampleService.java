package com.example.batch.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SampleService {

  public void process() throws Exception {
    log.info("--- Starting batch process ---");

    // Add your business logic here
    // Example:
    // - Data validation
    // - Data transformation
    // - External system integration

    log.info("--- Batch process completed ---");
  }
}
