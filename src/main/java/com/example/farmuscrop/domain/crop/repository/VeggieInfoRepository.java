package com.example.farmuscrop.domain.crop.repository;

import com.example.farmuscrop.domain.crop.document.VeggieInfo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VeggieInfoRepository extends MongoRepository<VeggieInfo, ObjectId> {

    Optional<VeggieInfo> findByName(String name);
}
