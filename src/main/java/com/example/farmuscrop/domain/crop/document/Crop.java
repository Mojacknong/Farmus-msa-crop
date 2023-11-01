package com.example.farmuscrop.domain.crop.document;

import com.example.farmuscrop.common.BaseDocument;
import lombok.AllArgsConstructor;
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

    static class Step {

        @Indexed(direction = IndexDirection.ASCENDING)
        private int num;
        private String content;
        private List<String> tips;
    }
}


