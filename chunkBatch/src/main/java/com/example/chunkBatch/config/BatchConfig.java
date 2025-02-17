package com.example.chunkBatch.config;

import com.example.chunkBatch.step.ChunkProcessor;
import com.example.chunkBatch.step.ChunkReader;
import com.example.chunkBatch.step.ChunkWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

  @Bean
  public Job chunkJob(JobRepository jobRepository, Step chunkStep) {
    return new JobBuilder("chunkJob", jobRepository).start(chunkStep).build();
  }

  @Bean
  public Step chunkStep(
      JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      ChunkReader reader) {
    return new StepBuilder("chunkStep", jobRepository)
        .<String, String>chunk(10, transactionManager)
        .reader(reader)
        .processor(new ChunkProcessor())
        .writer(new ChunkWriter())
        .build();
  }

  @Bean
  @StepScope
  public ChunkReader chunkReader(@Value("#{jobParameters['names']}") String names) {
    return new ChunkReader(names);
  }
}
