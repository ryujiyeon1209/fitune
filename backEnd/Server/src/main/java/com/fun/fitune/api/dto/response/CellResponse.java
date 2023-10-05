package com.fun.fitune.api.dto.response;

import com.fun.fitune.db.domain.Cell;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CellResponse {
    int cellSeq;
    String cellName;
    long cellExp;
    int cellLatestExp;
    int userSeq;

    public CellResponse(Cell cell){
        this.cellSeq = cell.getCellSeq();
        this.cellName = cell.getCellName();
        this.cellExp = cell.getCellExp();
        this.cellLatestExp = cell.getCellLatestExp();
        this.userSeq = cell.getUser().getUserSeq();
    }
}
