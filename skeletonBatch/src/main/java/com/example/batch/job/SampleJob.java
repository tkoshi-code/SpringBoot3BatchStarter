package com.example.batch.job;

import com.example.batch.logic.SampleLogic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SampleJob {

  private final SampleLogic logic;

  private static final String BATCH_JOB_NAME = "sample";

  @Bean("sample")
  public Job sample(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    log.info("----------- START ----------- Registering SampleJob ----------- START -----------");

    Step myStep =
        new StepBuilder(BATCH_JOB_NAME + "-step", jobRepository)
            .tasklet(logic, transactionManager)
            .build();

    Job myJob =
        new JobBuilder(BATCH_JOB_NAME, jobRepository)
            .incrementer(new RunIdIncrementer())
            .listener(BATCH_JOB_NAME)
            .start(myStep)
            .build();

    log.info("-----------  END  ----------- Registering SampleJob -----------  END  -----------");

    return myJob;
  }
}
