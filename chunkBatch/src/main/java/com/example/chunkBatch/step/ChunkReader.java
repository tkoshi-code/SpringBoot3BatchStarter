package com.example.chunkBatch.step;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;
import org.springframework.beans.factory.annotation.Value;

@StepScope
public class ChunkReader extends AbstractItemStreamItemReader<String> {

  private int count = 0;
  private final String[] names;

  public ChunkReader(@Value("#{jobParameters['names']}") String namesParam) {
    this.names = namesParam != null ? namesParam.split(",") : new String[] {"World"};
  }

  @Override
  public String read() {
    if (count < 10) {
      String name = names[count % names.length];
      count++;
      return name;
    }
    return null; // No more items to read
  }
}
