package com.example.farmuscrop.domain.history.document;

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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(collection = "history")
public class History extends BaseDocument {

    @Id
    private ObjectId id;

    @Indexed(direction = IndexDirection.ASCENDING)
    private Long userId;

    private List<HistoryDetail> historyDetails;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class HistoryDetail {

        private String image;
        private String veggieName;
        private String veggieNickname;
        private String period;
        private List<HistoryPost> posts;

        @AllArgsConstructor
        @NoArgsConstructor
        @Getter
        @Builder
        public static class HistoryPost {

            private String postImage;
            private String content;
            private LocalDateTime dateTime;
        }
    }
}
