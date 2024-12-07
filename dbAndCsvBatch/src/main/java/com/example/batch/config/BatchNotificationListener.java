package com.example.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BatchNotificationListener implements JobExecutionListener {
  @Override
  public void beforeJob(JobExecution jobExecution) {
    log.info(
        "----------- JOB [Job Name:{}] START! -----------",
        jobExecution.getJobInstance().getJobName());
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    log.info(
        "----------- JOB [Job Name:{}] FINISHED! status:[{}] -----------",
        jobExecution.getJobInstance().getJobName(),
        jobExecution.getStatus());
  }
}
