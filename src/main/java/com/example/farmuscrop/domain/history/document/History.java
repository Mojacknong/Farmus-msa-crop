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
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(collection = "history")
public class History extends BaseDocument {

    @Id
    private ObjectId id;

    private Long userId;

    private List<Detail> veggieHistoryDetails;
    private List<Detail> farmClubHistoryDetails;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class Detail {

        private ObjectId detailId;
        private String image;
        private String veggieName;
        private String name;
        private String period;

        public static Detail createDetail(ObjectId detailId, String image, String veggieName, String name, String period) {
            return Detail.builder()
                    .detailId(detailId)
                    .image(image)
                    .veggieName(veggieName)
                    .name(name)
                    .period(period)
                    .build();
        }
    }

    public static History createHistory(Long userId) {
        return History.builder()
                .userId(userId)
                .veggieHistoryDetails(new ArrayList<>())
                .farmClubHistoryDetails(new ArrayList<>())
                .build();
    }

    public void addVeggieHistoryDetail(Detail detail) {
        veggieHistoryDetails.add(detail);
    }

    public void addFarmClubHistoryDetail(Detail detail) {
        farmClubHistoryDetails.add(detail);
    }
}
