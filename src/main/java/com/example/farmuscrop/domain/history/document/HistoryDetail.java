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

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(collection = "history_detail")
public class HistoryDetail extends BaseDocument {

    @Id
    private ObjectId detailId;

    private List<HistoryPost> diaryPosts;
    private HistoryPost farmResult;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class HistoryPost {

        private String postImage;
        private String content;
        private LocalDateTime dateTime;
    }

    public static HistoryDetail createHistoryDetail(List<HistoryPost> diaryPosts, HistoryPost farmResult) {
        return HistoryDetail.builder()
                .diaryPosts(diaryPosts)
                .farmResult(farmResult)
                .build();
    }

    public void updateHistoryDetailResult(HistoryPost farmResult) {
        this.farmResult = farmResult;
    }
}
