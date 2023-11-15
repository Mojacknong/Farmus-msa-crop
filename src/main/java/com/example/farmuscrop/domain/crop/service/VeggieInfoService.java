package com.example.farmuscrop.domain.crop.service;

import com.example.farmuscrop.domain.crop.document.VeggieInfo;
import com.example.farmuscrop.domain.crop.dto.req.CreateVeggieInfoRequestDto;
import com.example.farmuscrop.domain.crop.dto.res.CreateVeggieInfoResponseDto;
import com.example.farmuscrop.domain.crop.dto.res.GetVeggieInfoDto;
import com.example.farmuscrop.domain.crop.repository.VeggieInfoRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VeggieInfoService {

    private final VeggieInfoRepository veggieInfoRepository;
    private final MongoTemplate mongoTemplate;

    public CreateVeggieInfoResponseDto saveVeggieInfo(CreateVeggieInfoRequestDto requestDto) {
        VeggieInfo veggieInfo = createVeggieInfo(requestDto);
        VeggieInfo newVeggieInfo = veggieInfoRepository.save(veggieInfo);

        return CreateVeggieInfoResponseDto.of(newVeggieInfo.getId());
    }

    public List<GetVeggieInfoDto> getCropInfoList() {
        List<VeggieInfo> veggieInfos = findAllVeggieInfo();

        // get 0 ~ 3 random int
        return veggieInfos.stream()
                .map(veggieInfo ->
                        GetVeggieInfoDto.of(
                                veggieInfo.getId().toHexString(),
                                veggieInfo.getName(),
                                veggieInfo.getDifficulty(),
                                veggieInfo.getVeggieImage(),
                                veggieInfo.getVeggieGrayImage()))
                .collect(Collectors.toList());
    }

    public List<VeggieInfo.Step> getSteps(ObjectId id) {
        Optional<VeggieInfo> crop = veggieInfoRepository.findById(id);

        return crop.map(VeggieInfo::getSteps).orElse(null);
    }

    public VeggieInfo createVeggieInfo(CreateVeggieInfoRequestDto requestDto) {
        return VeggieInfo.createVeggieInfo(
                requestDto.getName(),
                requestDto.getDifficulty(),
                requestDto.getSteps(),
                requestDto.getVeggieImage(),
                requestDto.getVeggieGrayImage(),
                requestDto.getFarmClubImageUrls(),
                requestDto.getFarmClubGrayImage()
        );
    }

    public Boolean checkVeggieInfoPresent(String name) {
        return veggieInfoRepository.findByName(name).isPresent();
    }

    public List<VeggieInfo> findAllVeggieInfo() {
        return veggieInfoRepository.findAll();
    }
}
