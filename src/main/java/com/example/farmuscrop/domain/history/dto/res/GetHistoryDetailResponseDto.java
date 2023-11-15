package com.example.farmuscrop.domain.history.dto.res;

import com.example.farmuscrop.domain.history.document.HistoryDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Getter
public class GetHistoryDetailResponseDto {

    private List<HistoryDetail.HistoryPost> diaryPosts;
    private HistoryDetail.HistoryPost farmResult;
}
