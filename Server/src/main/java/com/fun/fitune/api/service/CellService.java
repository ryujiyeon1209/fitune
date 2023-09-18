package com.fun.fitune.api.service;

import com.fun.fitune.api.dto.request.CellRequest;
import com.fun.fitune.db.domain.Cell;
import com.fun.fitune.db.domain.User;
import com.fun.fitune.db.repository.CellRepository;
import com.fun.fitune.db.repository.UserRepository;
import com.fun.fitune.exception.CustomException;
import com.fun.fitune.exception.CustomExceptionList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j

public class CellService {
    private final UserRepository userRepository;
    private final CellRepository cellRepository;

    @Transactional(readOnly = false)
    public Cell addCell(CellRequest cellReq) {
        User user = userRepository.findByUserSeq(cellReq.getUserSeq())
                .orElseThrow(()-> new CustomException(CustomExceptionList.USER_NOT_FOUND_ERROR));

        String name = cellReq.getCellName();

        Cell cell = Cell.builder()
                .cellName(name)
                .user(user)
                .build();

        return cellRepository.save(cell);
    }

}
