package com.example.chunkBatch.step;

import org.springframework.batch.item.ItemProcessor;

public class ChunkProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String item) {
        return "Hello " + item;
    }
}