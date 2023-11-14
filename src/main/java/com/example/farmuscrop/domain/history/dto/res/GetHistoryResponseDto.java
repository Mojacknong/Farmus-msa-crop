package com.example.farmuscrop.domain.history.dto.res;

import com.example.farmuscrop.domain.history.document.History;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Getter
public class GetHistoryResponseDto {

    private String historyId;
    private List<History.Detail> veggieHistoryDetails;
    private List<History.Detail> farmClubHistoryDetails;
}
