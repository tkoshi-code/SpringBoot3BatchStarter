package com.example.batch.job;

import com.example.batch.config.BatchNotificationListener;
import com.example.batch.logic.DbToCsvLogic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
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
public class DbToCsvJob {

  private final DbToCsvLogic logic;
  private final BatchNotificationListener listener;

  private static final String BATCH_NAME = "DB_TO_CSV";

  @Bean
  public Job createDbToCsvJob(
      JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    log.info("Registering job: {}", BATCH_NAME);

    Job myJob =
        new JobBuilder(BATCH_NAME, jobRepository)
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .start(
                new StepBuilder(BATCH_NAME + "-step", jobRepository)
                    .tasklet(logic, transactionManager)
                    .build())
            .build();

    log.info("Job registered successfully: {} ", myJob.getName());

    return myJob;
  }
}
