package com.example.farmuscrop.domain.crop.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Getter
public class GetAllStepNameResponseDto {

    private List<String> stepList;
}
