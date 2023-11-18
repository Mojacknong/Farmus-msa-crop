package com.example.farmuscrop.domain.history.repository;

import com.example.farmuscrop.domain.history.document.HistoryClubDetail;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryClubDetailRepository extends MongoRepository<HistoryClubDetail, ObjectId> {
}
