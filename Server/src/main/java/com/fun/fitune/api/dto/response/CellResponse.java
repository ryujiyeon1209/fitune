package com.fun.fitune.api.dto.response;

import com.fun.fitune.db.domain.Cell;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CellResponse {
    int cellSeq;
    String cellName;
    long cellExp;
    int userSeq;
    int cellLatestExp;

    public CellResponse(Cell cell){
        this.cellSeq = cell.getCellSeq();
        this.cellName = cell.getCellName();
        this.cellExp = cell.getCellExp();
        this.userSeq = cell.getUser().getUserSeq();
        this.cellLatestExp = cell.getCellLatestExp();
    }
}
