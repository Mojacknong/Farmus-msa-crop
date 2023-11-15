package com.example.farmuscrop.domain.history.repository;


import com.example.farmuscrop.domain.history.document.History;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HistoryRepository extends MongoRepository<History, ObjectId> {

    boolean existsByUserId(Long userId);

    Optional<History> findByUserId(Long userId);
}
