package com.example.farmuscrop.domain.crop.dto.req;

import com.example.farmuscrop.domain.crop.document.Crop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCropRequestDto {

    private String name;
    private String difficulty;
    private List<String> imageUrl;
    private List<Crop.Step> steps;
}
