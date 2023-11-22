package com.example.farmuscrop.domain.crop.service;

import com.example.farmuscrop.domain.crop.document.VeggieInfo;
import com.example.farmuscrop.domain.crop.dto.req.CreateVeggieInfoRequestDto;
import com.example.farmuscrop.domain.crop.dto.res.*;
import com.example.farmuscrop.domain.crop.repository.VeggieInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public GetVeggieInfoResponseDto getVeggieInfoDetail(ObjectId id) {
        VeggieInfo veggieInfo = veggieInfoRepository.findById(id).orElse(null);

        // 0~3 랜덤 정수
        int random = (int)(Math.random() * 4);

        return GetVeggieInfoResponseDto.of(
                veggieInfo.getName(),
                veggieInfo.getDifficulty(),
                veggieInfo.getFarmClubImageUrls().get(random),
                veggieInfo.getFarmClubGrayImage(),
                veggieInfo.getSteps().size(),
                veggieInfo.getSteps().get(0).getContent()
        );
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

    public GetStepNameResponseDto getVeggieInfoStepName(ObjectId id, int step) {
        VeggieInfo crop = veggieInfoRepository.findById(id).orElse(null);

        log.info("crop: {}", crop);

        return GetStepNameResponseDto.of(crop.getSteps().get(step).getContent());
    }

    public List<GetStepsWithTipResponseDto> getStepsWithTip(ObjectId id) {
        VeggieInfo crop = veggieInfoRepository.findById(id).orElse(null);

        return crop.getSteps().stream()
                .map(step -> GetStepsWithTipResponseDto.of(
                        step.getContent(),
                        step.getTips().get((int) (Math.random() * step.getTips().size()))
                ))
                .collect(Collectors.toList());
    }

    public List<List<GetStepsWithTipResponseDto>> getStepsWithTip(ObjectId id, int stepNum) {
        VeggieInfo crop = veggieInfoRepository.findById(id).orElse(null);

        List<GetStepsWithTipResponseDto> postList = new ArrayList<>();
        List<GetStepsWithTipResponseDto> curList = new ArrayList<>();
        List<GetStepsWithTipResponseDto> preList = new ArrayList<>();

        List<GetStepsWithTipResponseDto> list = crop.getSteps().stream()
                .map(step -> GetStepsWithTipResponseDto.of(
                        step.getContent(),
                        step.getTips().get((int) (Math.random() * step.getTips().size()))
                ))
                .collect(Collectors.toList());

        for (int i = 0; i < list.size(); i++) {
            if (i < stepNum) {
                preList.add(list.get(i));
            } else if (i == stepNum) {
                curList.add(list.get(i));
            } else {
                postList.add(list.get(i));
            }
        }

        List<List<GetStepsWithTipResponseDto>> result = new ArrayList<>();
        result.add(preList);
        result.add(curList);
        result.add(postList);

        return result;
    }

    public GetAllStepNameResponseDto getAllStepName(ObjectId id) {
        VeggieInfo crop = veggieInfoRepository.findById(id).orElse(null);

        return GetAllStepNameResponseDto.of(
                crop.getSteps().stream()
                        .map(VeggieInfo.Step::getContent)
                        .collect(Collectors.toList())
        );
    }

    public Boolean checkVeggieInfoPresent(String name) {
        return veggieInfoRepository.findByName(name).isPresent();
    }

    public List<VeggieInfo> findAllVeggieInfo() {
        return veggieInfoRepository.findAll();
    }
}
