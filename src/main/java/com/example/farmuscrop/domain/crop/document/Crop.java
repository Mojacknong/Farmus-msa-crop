package com.example.farmuscrop.domain.crop.document;

import com.example.farmuscrop.common.BaseDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
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
    private ObjectId id;

    private String name;

    private String difficulty;

    private List<Step> steps;

    private List<String> imageUrl;

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

    public static Crop createCrop(String name, String difficulty, List<Step> steps, List<String> imageUrl) {
        return Crop.builder()
                .name(name)
                .difficulty(difficulty)
                .steps(steps)
                .imageUrl(imageUrl)
                .build();
    }
}


