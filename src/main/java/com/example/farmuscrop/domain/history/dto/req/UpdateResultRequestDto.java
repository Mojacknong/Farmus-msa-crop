package com.example.farmuscrop.domain.history.dto.req;

import com.example.farmuscrop.domain.history.document.History;
import com.example.farmuscrop.domain.history.document.HistoryDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateResultRequestDto {

    private ObjectId historyDetailId;
    private HistoryDetail.HistoryPost result;
}
