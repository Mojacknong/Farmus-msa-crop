package com.example.farmuscrop.domain.history.repository;

import com.example.farmuscrop.domain.history.document.HistoryDetail;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryDetailRepository extends MongoRepository<HistoryDetail, ObjectId> {
}
