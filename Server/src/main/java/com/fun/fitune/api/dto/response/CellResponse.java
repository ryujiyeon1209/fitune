package com.fun.fitune.api.dto.response;

import com.fun.fitune.db.domain.Cell;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CellResponse {
    String cellName;
    int cellExp;
    int userSeq;
    public CellResponse(Cell cell){
        this.cellName = cell.getCellName();
        this.cellExp = cell.getCellExp();
        this.userSeq = cell.getCellSeq();
    }
}
