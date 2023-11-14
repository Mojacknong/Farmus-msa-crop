package com.example.farmuscrop.domain.crop.repository;

import com.example.farmuscrop.domain.crop.document.Crop;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CropRepository extends MongoRepository<Crop, ObjectId> {

    Optional<Crop> findByName(String name);
}
