package com.example.farmuscrop.domain.history.service;

import com.example.farmuscrop.domain.history.document.History;
import com.example.farmuscrop.domain.history.document.HistoryClubDetail;
import com.example.farmuscrop.domain.history.document.HistoryDetail;
import com.example.farmuscrop.domain.history.dto.req.CreateHistoryClubDetailRequestDto;
import com.example.farmuscrop.domain.history.dto.req.CreateHistoryDetailRequestDto;
import com.example.farmuscrop.domain.history.dto.req.UpdateResultRequestDto;
import com.example.farmuscrop.domain.history.dto.res.*;
import com.example.farmuscrop.domain.history.repository.HistoryClubDetailRepository;
import com.example.farmuscrop.domain.history.repository.HistoryDetailRepository;
import com.example.farmuscrop.domain.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final HistoryDetailRepository historyDetailRepository;
    private final HistoryClubDetailRepository historyClubDetailRepository;

    public CreateHistoryResponseDto createUserHistory(Long userId) {
        validateHistory(userId);
        History newHistory = createHistory(userId);
        History saved = historyRepository.save(newHistory);

        return CreateHistoryResponseDto.of(saved.getId().toHexString());
    }

    public CreateHistoryDetailResponseDto createHistoryDetail(Long userId, CreateHistoryDetailRequestDto requestDto) {
        History history = getHistory(userId);
        HistoryDetail newHistoryDetail = createHistoryDetail(requestDto);
        ObjectId savedId = historyDetailRepository.save(newHistoryDetail).getId();

        History.Detail newDetail = createDetail(
                savedId.toHexString(),
                requestDto.getImage(),
                requestDto.getVeggieName(),
                requestDto.getName(),
                requestDto.getPeriod()
        );

        history.addVeggieHistoryDetail(newDetail);

        History saved = historyRepository.save(history);

        return CreateHistoryDetailResponseDto.of(saved.getId().toHexString());
    }

    public CreateHistoryClubDetailResponseDto createHistoryClubDetail(Long userId, CreateHistoryClubDetailRequestDto requestDto) {
        History history = getHistory(userId);
        HistoryClubDetail newHistoryClubDetail = createHistoryClubDetail(requestDto);
        ObjectId savedId = historyClubDetailRepository.save(newHistoryClubDetail).getId();

        History.Detail newDetail = createDetail(
                savedId.toHexString(),
                requestDto.getImage(),
                requestDto.getVeggieName(),
                requestDto.getName(),
                requestDto.getPeriod()
        );

        history.addFarmClubHistoryDetail(newDetail);

        History saved = historyRepository.save(history);

        return CreateHistoryClubDetailResponseDto.of(saved.getId().toHexString());
    }

    public UpdateResultResponseDto updateDetailResult(UpdateResultRequestDto requestDto) {
        HistoryDetail detail = getHistoryDetail(new ObjectId(requestDto.getHistoryDetailId()));

        detail.updateHistoryDetailResult(requestDto.getResult());

        HistoryDetail saved = historyDetailRepository.save(detail);

        return UpdateResultResponseDto.of(saved.getId().toHexString());
    }

    public GetHistoryResponseDto getUserHistoryDetails(Long userId) {
        History history = getHistory(userId);

        return GetHistoryResponseDto.of(
                history.getId().toHexString(),
                history.getVeggieHistoryDetails(),
                history.getFarmClubHistoryDetails()
        );
    }

    public GetHistoryDetailResponseDto getUserHistoryDetail(ObjectId historyDetailId) {
        HistoryDetail detail = getHistoryDetail(historyDetailId);

        return GetHistoryDetailResponseDto.of(
                detail.getDiaryPosts(),
                detail.getFarmResult()
        );
    }

    public GetHistoryClubDetailResponseDto getUserHistoryClubDetail(ObjectId historyClubDetailId) {
        HistoryClubDetail detail = getHistoryClubDetail(historyClubDetailId);

        return GetHistoryClubDetailResponseDto.of(
                detail.getMissionPostList()
        );
    }

    public HistoryDetail saveHistoryDetail(HistoryDetail historyDetail) {
        return historyDetailRepository.save(historyDetail);
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

    public HistoryClubDetail createHistoryClubDetail(CreateHistoryClubDetailRequestDto requestDto) {
        return HistoryClubDetail.createHistoryClubDetail(
                requestDto.getMissionPosts()
        );
    }

    public HistoryDetail getHistoryDetail(ObjectId historyDetailId) {
        return historyDetailRepository.findById(historyDetailId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기록입니다."));
    }

    public HistoryClubDetail getHistoryClubDetail(ObjectId historyClubDetailId) {
        return historyClubDetailRepository.findById(historyClubDetailId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기록입니다."));
    }

    public History.Detail createDetail(String detailId, String image, String veggieName, String name, String period) {
        return History.Detail.createDetail(detailId, image, veggieName, name, period);
    }
}
