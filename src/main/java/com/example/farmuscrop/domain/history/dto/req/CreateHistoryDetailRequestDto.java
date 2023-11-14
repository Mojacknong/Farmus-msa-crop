package com.example.farmuscrop.domain.history.dto.req;

import com.example.farmuscrop.domain.history.document.History;
import com.example.farmuscrop.domain.history.document.HistoryDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateHistoryDetailRequestDto {

    private boolean isVeggie;

    private String image;
    private String veggieName;
    private String name;
    private String period;

    private List<HistoryDetail.HistoryPost> diaryPosts;
    private HistoryDetail.HistoryPost farmResult;
}
