package com.example.farmuscrop.domain.crop.repository;

import com.example.farmuscrop.domain.crop.document.Crop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CropRepository extends MongoRepository<Crop, String> {

    Optional<Crop> findByName(String name);
}
