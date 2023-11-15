package com.example.farmuscrop.domain.crop.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bson.types.ObjectId;

@AllArgsConstructor(staticName = "of")
@Getter
public class CreateVeggieInfoResponseDto {

    private ObjectId id;
}
