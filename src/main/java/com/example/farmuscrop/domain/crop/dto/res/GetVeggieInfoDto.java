package com.example.farmuscrop.domain.crop.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Getter
public class GetVeggieInfoDto {

    private String id;
    private String name;
    private String difficulty;
    private String imageUrl;
    private String grayImageUrl;
}
