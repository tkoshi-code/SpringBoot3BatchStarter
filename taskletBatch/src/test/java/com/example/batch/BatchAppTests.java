package com.example.batch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBatchTest
@SpringBootTest
class BatchAppTests {

  @Autowired private JobLauncherTestUtils jobLauncherTestUtils;

  @Autowired private Job sampleJob;

  @Test
  void testSampleJobExecution() throws Exception {
    jobLauncherTestUtils.setJob(sampleJob);

    JobExecution jobExecution = jobLauncherTestUtils.launchJob();
    assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
  }
}
