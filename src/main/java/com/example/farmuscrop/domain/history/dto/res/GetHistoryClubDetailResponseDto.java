package com.example.farmuscrop.domain.history.dto.res;

import com.example.farmuscrop.domain.history.document.HistoryClubDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Getter
public class GetHistoryClubDetailResponseDto {

    private List<HistoryClubDetail.HistoryClubPost> missionPosts;
}
