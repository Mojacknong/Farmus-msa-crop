package com.example.farmuscrop.domain.crop.dto.req;

import com.example.farmuscrop.domain.crop.document.VeggieInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateVeggieInfoRequestDto {

    private String name;
    private String difficulty;
    private String veggieImage;
    private String veggieGrayImage;
    private List<String> farmClubImageUrls;
    private String farmClubGrayImage;
    private List<VeggieInfo.Step> steps;
}
