package com.example.farmuscrop.domain.crop.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Getter
public class GetCropNameAndDifficultyDto {

    private String name;
    private String difficulty;
}
