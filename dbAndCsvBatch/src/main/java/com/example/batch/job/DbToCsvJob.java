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

  /**
   * バッチジョブの名前を定義。この名前は実行時引数で指定するジョブ名と一致する必要がある。
   *
   * <p>Defines the name of the batch job. This must match the job name provided in the runtime
   * arguments.
   */
  private static final String BATCH_NAME = "DB_TO_CSV";

  @Bean
  public Job createDbToCsvJob(
      JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    log.info("Registering job: {}", BATCH_NAME);

    Job myJob =
        new JobBuilder(BATCH_NAME + "-job", jobRepository)
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
