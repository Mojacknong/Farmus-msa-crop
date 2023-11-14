package com.example.farmuscrop.domain.crop.service;

import com.example.farmuscrop.domain.crop.document.Crop;
import com.example.farmuscrop.domain.crop.dto.req.CreateCropRequestDto;
import com.example.farmuscrop.domain.crop.dto.res.CreateCropResponseDto;
import com.example.farmuscrop.domain.crop.dto.res.GetCropInfoDto;
import com.example.farmuscrop.domain.crop.repository.CropRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CropService {

    private final CropRepository cropRepository;
    private final MongoTemplate mongoTemplate;

    public CreateCropResponseDto saveCrop(CreateCropRequestDto requestDto) {
        Crop crop = createCrop(requestDto);
        Crop newCrop = cropRepository.save(crop);

        return CreateCropResponseDto.of(newCrop.getId());
    }

    public List<GetCropInfoDto> getCropInfoList() {
        List<Crop> crops = findAllCrops();

        // get 0 ~ 3 random int
        return crops.stream()
                .map(crop -> GetCropInfoDto.of(crop.getId().toHexString(), crop.getName(), crop.getDifficulty(), crop.getImageUrl().get(Math.toIntExact(Math.round(Math.random() * 3)))))
                .collect(Collectors.toList());
    }

    public List<Crop.Step> getSteps(ObjectId id) {
        Optional<Crop> crop = cropRepository.findById(id);

        return crop.map(Crop::getSteps).orElse(null);
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

    public List<Crop> findAllCrops() {
        return cropRepository.findAll();
    }
}
