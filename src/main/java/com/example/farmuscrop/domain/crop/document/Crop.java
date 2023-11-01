package com.example.farmuscrop.domain.crop.document;

import com.example.farmuscrop.common.BaseDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(collection = "crops")
public class Crop extends BaseDocument {

    @Id
    private String id;

    private String name;

    private String difficulty;

    private List<Step> steps;

    private String imageUrl;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class Step {

        @Indexed(direction = IndexDirection.ASCENDING)
        private int num;
        private String content;
        private List<String> tips;
    }

    public Crop createCrop(String name, String difficulty, List<Step> steps, String imageUrl) {
        return Crop.builder()
                .name(name)
                .difficulty(difficulty)
                .steps(steps)
                .imageUrl(imageUrl)
                .build();
    }

    public Step createStep(int num, String content, List<String> tips) {
        return Step.builder()
                .num(num)
                .content(content)
                .tips(tips)
                .build();
    }
}


