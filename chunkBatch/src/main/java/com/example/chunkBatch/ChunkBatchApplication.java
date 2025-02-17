package com.example.chunkBatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChunkBatchApplication implements CommandLineRunner {

  @Autowired private JobLauncher jobLauncher;

  @Autowired private Job chunkJob;

  public static void main(String[] args) {
    SpringApplication.run(ChunkBatchApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    String names = "World";
    for (String arg : args) {
      if (arg.startsWith("--names=")) {
        names = arg.substring("--names=".length());
        break;
      }
    }

    JobParameters params =
        new JobParametersBuilder()
            .addString("names", names)
            .addLong("time", System.currentTimeMillis())
            .toJobParameters();
    jobLauncher.run(chunkJob, params);
  }
}
