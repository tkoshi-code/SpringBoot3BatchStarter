package com.example.batch.logic;

import com.example.batch.enums.BatchResult;
import com.example.batch.service.CsvToDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CsvToDbLogic implements Tasklet {
  private final CsvToDbService csvToDbService;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
    log.info("----------- START ----------- CsvToDbLogic ----------- START -----------");

    try {
      BatchResult result = csvToDbService.execute();

      if (result == BatchResult.SUCCESS) {
        log.info("Batch process completed successfully.");
      } else {
        log.error("Batch process failed with result: {}", result);
        contribution.setExitStatus(ExitStatus.FAILED);
      }
    } catch (Exception e) {
      log.error("An error occurred during batch processing", e);
      contribution.setExitStatus(ExitStatus.FAILED);
    }

    log.info("-----------  END  ----------- CsvToDbLogic -----------  END  -----------");

    return RepeatStatus.FINISHED;
  }
}
