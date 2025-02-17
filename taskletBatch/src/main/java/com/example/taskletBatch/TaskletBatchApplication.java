package com.example.taskletBatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskletBatchApplication implements CommandLineRunner {

  @Autowired private JobLauncher jobLauncher;

  @Autowired private Job helloJob;

  public static void main(String[] args) {
    SpringApplication.run(TaskletBatchApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    String name = "World";
    for (String arg : args) {
      if (arg.startsWith("--name=")) {
        name = arg.substring("--name=".length());
        break;
      }
    }

    JobParameters params =
        new JobParametersBuilder()
            .addString("name", name)
            .addLong("time", System.currentTimeMillis())
            .toJobParameters();
    jobLauncher.run(helloJob, params);
  }
}
