package com.fun.fitune.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BattleRecordResponse {
    int battleRecordSeq;
    LocalDateTime battleDate;
    String winnerName;
    int battleOtherSeq;
    String battleOtherName;
}
