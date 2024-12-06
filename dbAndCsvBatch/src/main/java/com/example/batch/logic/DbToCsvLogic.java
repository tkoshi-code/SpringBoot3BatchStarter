package com.example.batch.logic;

import com.example.batch.service.DbToCsvService;
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
public class DbToCsvLogic implements Tasklet {
  private final DbToCsvService dbToCsvService;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws Exception {

    log.info("----------- START ----------- DbToCsvLogic ----------- START -----------");
    try {
      dbToCsvService.execute();
    } catch (Exception e) {
      contribution.setExitStatus(ExitStatus.FAILED);
      log.error("job failure", e);
    }
    log.info("-----------  END  ----------- DbToCsvLogic -----------  END  -----------");

    return RepeatStatus.FINISHED;
  }
}
