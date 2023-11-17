package com.example.farmuscrop.domain.crop.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Getter
public class GetVeggieInfoResponseDto {

    private String veggieName;
    private String difficulty;
    private String imageUrl;
    private String grayImageUrl;
    private int stepNum;
    private String firstStepName;
}
