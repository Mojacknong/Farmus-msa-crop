package com.example.farmuscrop.domain.crop.service;

import com.example.farmuscrop.domain.crop.document.Crop;
import com.example.farmuscrop.domain.crop.dto.req.CreateCropRequestDto;
import com.example.farmuscrop.domain.crop.dto.res.CreateCropResponseDto;
import com.example.farmuscrop.domain.crop.repository.CropRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CropService {

    private final CropRepository cropRepository;
    private final MongoTemplate mongoTemplate;

//    public Crop.Step findStepByNum(int num) {
//        return mongoTemplate.findById(num, Crop.Step.class);
//    }
//
//    public List<Crop.Step> findAllSteps() {
//        return mongoTemplate.findAll(Crop.Step.class);
//    }

    public CreateCropResponseDto saveCrop(CreateCropRequestDto requestDto) {
        Crop crop = createCrop(requestDto);
        cropRepository.save(crop);

        return CreateCropResponseDto.of(crop.getId());
    }

    public Crop createCrop(CreateCropRequestDto requestDto) {
        return Crop.createCrop(
                requestDto.getName(),
                requestDto.getDifficulty(),
                requestDto.getSteps(),
                requestDto.getImageUrl()
        );
    }

    public Boolean checkCropPresent(String name) {
        return cropRepository.findByName(name).isPresent();
    }
}
