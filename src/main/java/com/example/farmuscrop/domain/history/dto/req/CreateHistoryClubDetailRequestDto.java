package com.example.farmuscrop.domain.history.dto.req;

import com.example.farmuscrop.domain.history.document.HistoryClubDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateHistoryClubDetailRequestDto {

    private String image;
    private String veggieName;
    private String name;
    private String period;

    private List<HistoryClubDetail.HistoryClubPost> missionPosts;
}
