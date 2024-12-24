package com.example.batch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.batch.enums.BatchResult;
import com.example.batch.logic.CsvToDbLogic;
import com.example.batch.logic.DbToCsvLogic;
import com.example.batch.service.CsvToDbService;
import com.example.batch.service.DbToCsvService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

@TestPropertySource(
    properties = {
      "spring.batch.job.enabled=false",
      "spring.datasource.mysqlmain.url=jdbc:mysql://localhost:3306/sampledb",
      "spring.datasource.mysqlmain.username=sampleuser",
      "spring.datasource.mysqlmain.password=samplepassword"
    })
@SpringBootTest
@SpringBatchTest
class BatchJobTest {

  @Autowired private JobLauncherTestUtils jobLauncherTestUtils;

  @Autowired private JobRepositoryTestUtils jobRepositoryTestUtils;

  @Autowired
  @Qualifier("createDbToCsvJob")
  private Job dbToCsvJob;

  @Autowired
  @Qualifier("createCsvToDbJob")
  private Job csvToDbJob;

  @Autowired private DbToCsvLogic dbToCsvLogic;

  @Autowired private CsvToDbLogic csvToDbLogic;

  private DbToCsvService dbToCsvService;
  private CsvToDbService csvToDbService;

  @BeforeEach
  void setUp() {
    dbToCsvService = mock(DbToCsvService.class);
    csvToDbService = mock(CsvToDbService.class);

    ReflectionTestUtils.setField(dbToCsvLogic, "dbToCsvService", dbToCsvService);
    ReflectionTestUtils.setField(csvToDbLogic, "csvToDbService", csvToDbService);

    jobRepositoryTestUtils.removeJobExecutions();
  }

  @Test
  void testDbToCsvJobSuccess() throws Exception {
    System.setProperty("spring.batch.job.name", "DB_TO_CSV");
    jobLauncherTestUtils.setJob(dbToCsvJob);

    when(dbToCsvService.execute(any(), any())).thenReturn(BatchResult.SUCCESS);
    JobExecution jobExecution = jobLauncherTestUtils.launchJob();
    assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
    verify(dbToCsvService).execute(any(), any());
  }

  @Test
  void testCsvToDbJobSuccess() throws Exception {
    System.setProperty("spring.batch.job.name", "CSV_TO_DB");
    jobLauncherTestUtils.setJob(csvToDbJob);

    when(csvToDbService.execute(any())).thenReturn(BatchResult.SUCCESS);
    JobExecution jobExecution = jobLauncherTestUtils.launchJob();
    assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
    verify(csvToDbService).execute(any());
  }

  @Test
  void testDbToCsvJobFailure() throws Exception {
    System.setProperty("spring.batch.job.name", "DB_TO_CSV");
    jobLauncherTestUtils.setJob(dbToCsvJob);

    when(dbToCsvService.execute(any(), any())).thenThrow(new RuntimeException("Test exception"));
    JobExecution jobExecution = jobLauncherTestUtils.launchJob();
    assertEquals(ExitStatus.FAILED, jobExecution.getExitStatus());
  }

  @Test
  void testCsvToDbJobFailure() throws Exception {
    System.setProperty("spring.batch.job.name", "CSV_TO_DB");
    jobLauncherTestUtils.setJob(csvToDbJob);

    when(csvToDbService.execute(any())).thenThrow(new RuntimeException("Test exception"));
    JobExecution jobExecution = jobLauncherTestUtils.launchJob();
    assertEquals(ExitStatus.FAILED, jobExecution.getExitStatus());
  }
}
