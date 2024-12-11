package com.example.batch.logic;

import com.example.batch.enums.BatchResult;
import com.example.batch.service.DbToCsvService;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DbToCsvLogic implements Tasklet {
  private final DbToCsvService dbToCsvService;

  @Value("${batch.types:2,3,4}")
  private String typesConfig;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {

    try {
      // 起動引数からリストに変換
      // Convert the `batch.types` command-line argument into a list of Byte values
      List<Byte> types = Arrays.stream(typesConfig.split(",")).map(Byte::valueOf).toList();
      String filePath = "members.csv";
      BatchResult result = dbToCsvService.execute(types, filePath);

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

    return RepeatStatus.FINISHED;
  }
}
