package com.example.farmuscrop.domain.crop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface CropRepository extends MongoRepository<CropRepository, String> {
}
