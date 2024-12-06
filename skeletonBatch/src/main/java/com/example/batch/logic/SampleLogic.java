package com.example.batch.logic;

import com.example.batch.service.SampleService;
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
public class SampleLogic implements Tasklet {
  private final SampleService sampleService;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws Exception {

    log.info("----------- START ----------- SampleLogic ----------- START -----------");
    try {
      sampleService.execute();
    } catch (Exception e) {
      contribution.setExitStatus(ExitStatus.FAILED);
      log.error("job failure", e);
    }
    log.info("-----------  END  ----------- SampleLogic -----------  END  -----------");

    return RepeatStatus.FINISHED;
  }
}
