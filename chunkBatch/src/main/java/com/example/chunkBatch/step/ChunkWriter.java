package com.example.chunkBatch.step;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class ChunkWriter implements ItemWriter<String> {

    @Override
    public void write(Chunk<? extends String> chunk) {
        for (String item : chunk.getItems()) {
            System.out.println(item);
        }
    }
}
