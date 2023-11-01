package com.example.farmuscrop.domain.crop.service;

import com.example.farmuscrop.domain.crop.repository.CropRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CropService {

    private final CropRepository cropRepository;
}
