package com.example.batch.job;

import com.example.batch.logic.SampleLogic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@Component
public class SampleJob {

  private final SampleLogic logic;

  private static final String BATCH_NAME = "sample";

  @Bean
  public Job createSampleJob(
      JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    log.info("----------- Registering job: {} -----------", BATCH_NAME);

    Step myStep =
        new StepBuilder(BATCH_NAME + "-step", jobRepository)
            .tasklet(logic, transactionManager)
            .build();

    Job myJob = new JobBuilder(BATCH_NAME + "-job", jobRepository).start(myStep).build();

    log.info("----------- Job registered successfully: {} -----------", myJob.getName());
    return myJob;
  }
}
