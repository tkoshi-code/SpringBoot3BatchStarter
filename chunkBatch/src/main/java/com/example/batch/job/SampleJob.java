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
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SampleJob {

  private final JobRepository jobRepository;
  private final PlatformTransactionManager transactionManager;
  private final SampleLogic logic;

  @Bean
  public Job createSampleJob() {
    log.info("----------- Registering job: sample -----------");

    // Create a step for processing
    Step myStep =
        new StepBuilder("processing-step", jobRepository)
            .tasklet(logic, transactionManager)
            .build();

    // Build and return the job
    Job myJob = new JobBuilder("sample-job", jobRepository).start(myStep).build();

    log.info("----------- Job registered successfully: {} -----------", myJob.getName());

    return myJob;
  }
}
