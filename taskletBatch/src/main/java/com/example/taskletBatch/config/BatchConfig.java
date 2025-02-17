package com.example.taskletBatch.config;

import com.example.taskletBatch.tasklet.HelloTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

  @Bean
  public HelloTasklet helloTasklet() {
    return new HelloTasklet();
  }

  @Bean
  public Job helloJob(JobRepository jobRepository, Step helloStep) {
    return new JobBuilder("helloJob", jobRepository).start(helloStep).build();
  }

  @Bean
  public Step helloStep(
      JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("helloStep", jobRepository)
        .tasklet(helloTasklet(), transactionManager)
        .build();
  }
}
