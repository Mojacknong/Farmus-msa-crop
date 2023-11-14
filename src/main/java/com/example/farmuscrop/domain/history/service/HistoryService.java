package com.example.farmuscrop.domain.history.service;

import com.example.farmuscrop.domain.history.document.History;
import com.example.farmuscrop.domain.history.document.HistoryDetail;
import com.example.farmuscrop.domain.history.dto.req.CreateHistoryDetailRequestDto;
import com.example.farmuscrop.domain.history.dto.req.UpdateResultRequestDto;
import com.example.farmuscrop.domain.history.dto.res.CreateHistoryDetailResponseDto;
import com.example.farmuscrop.domain.history.dto.res.CreateHistoryResponseDto;
import com.example.farmuscrop.domain.history.dto.res.GetHistoryResponseDto;
import com.example.farmuscrop.domain.history.dto.res.UpdateResultResponseDto;
import com.example.farmuscrop.domain.history.repository.HistoryDetailRepository;
import com.example.farmuscrop.domain.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final HistoryDetailRepository historyDetailRepository;

    public CreateHistoryResponseDto createUserHistory(Long userId) {
        validateHistory(userId);
        History newHistory = createHistory(userId);
        History saved = historyRepository.save(newHistory);

        return CreateHistoryResponseDto.of(saved.getId().toHexString());
    }

    public CreateHistoryDetailResponseDto createUserHistoryDetail(Long userId, CreateHistoryDetailRequestDto requestDto) {
        History history = getHistory(userId);
        HistoryDetail newHistoryDetail = createHistoryDetail(requestDto);
        HistoryDetail savedDetail = historyDetailRepository.save(newHistoryDetail);

        History.Detail newDetail = createDetail(
                savedDetail.getDetailId(),
                requestDto.getImage(),
                requestDto.getVeggieName(),
                requestDto.getName(),
                requestDto.getPeriod()
        );

        if (requestDto.isVeggie()) {
            history.addVeggieHistoryDetail(newDetail);
        } else {
            history.addFarmClubHistoryDetail(newDetail);
        }

        History saved = historyRepository.save(history);

        return CreateHistoryDetailResponseDto.of(saved.getId().toHexString());
    }

    public UpdateResultResponseDto updateDetailResult(UpdateResultRequestDto requestDto) {
        HistoryDetail detail = getHistoryDetail(requestDto.getHistoryDetailId());

        detail.updateHistoryDetailResult(requestDto.getResult());

        HistoryDetail saved = historyDetailRepository.save(detail);

        return UpdateResultResponseDto.of(saved.getDetailId().toHexString());
    }

    public GetHistoryResponseDto getUserHistoryDetails(Long userId) {
        History history = getHistory(userId);

        return GetHistoryResponseDto.of(
                history.getId().toHexString(),
                history.getVeggieHistoryDetails(),
                history.getFarmClubHistoryDetails()
        );
    }

    public void validateHistory(Long userId) {
        if (historyRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException("이미 존재하는 유저입니다.");
        }
    }

    public History createHistory(Long userId) {
        return History.createHistory(userId);
    }

    public History getHistory(Long userId) {
        return historyRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }

    public HistoryDetail createHistoryDetail(CreateHistoryDetailRequestDto requestDto) {
        return HistoryDetail.createHistoryDetail(
                requestDto.getDiaryPosts(),
                requestDto.getFarmResult()
        );
    }

    public HistoryDetail getHistoryDetail(ObjectId historyDetailId) {
        return historyDetailRepository.findById(historyDetailId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기록입니다."));
    }

    public History.Detail createDetail(ObjectId detailId, String image, String veggieName, String name, String period) {
        return History.Detail.createDetail(detailId, image, veggieName, name, period);
    }
}
