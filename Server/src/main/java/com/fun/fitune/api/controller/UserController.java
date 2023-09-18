package com.fun.fitune.api.controller;

import com.fun.fitune.api.dto.request.CellRequest;
import com.fun.fitune.api.dto.response.CellResponse;
import com.fun.fitune.api.dto.response.CommonResponse;
import com.fun.fitune.api.dto.response.UserInfoResponse;
import com.fun.fitune.api.service.CellService;
import com.fun.fitune.api.service.UserService;
import com.fun.fitune.db.domain.Cell;
import com.fun.fitune.db.domain.User;
import com.fun.fitune.exception.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="사용자 관리 API", description="사용자 정보 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    final CellService cellService;
    final String SUCCESS = "SUCCESS";

    //사용자 정보 조회
    @Operation(summary = "사용자 정보 조회", description = "파라미터로 받은 userSeq와 일치하는 사용자 정보를 전달.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UserInfoResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Parameter(name = "userSeq", description = "조회할 사용자의 userId")
    @GetMapping("/info/{userSeq}")
    public ResponseEntity<CommonResponse<UserInfoResponse>> getUserInfo(@PathVariable("userSeq") int userSeq){
        System.out.println("사용자 정보 조회 ");
        User userInfo = userService.getUserInfo(userSeq);
        return new ResponseEntity<>(
                makeCommonResponse(SUCCESS, new UserInfoResponse(userInfo)), HttpStatus.OK);
    }

    //사용자 운동세포 생성 - 튜토리얼 종료 후
    @Operation(summary = "세포 생성", description = "튜토리얼 완료하면 세포가 생성됨")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = CellResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Parameter(name = "cellName", description = "생성할 세포 이름")
    @PostMapping("/cell/make")
    public ResponseEntity<CommonResponse<CellResponse>> makeUserCell(@RequestBody CellRequest cellReq) {
        //유저 아이디와 세포 이름을 얻어서 저장
        return new ResponseEntity<>(makeCommonResponse(SUCCESS,
                    new CellResponse(cellService.addCell(cellReq))), HttpStatus.OK);

    }

    //사용자 선호 운동 저장



    //사용자 이름 변경
//    @Operation(summary = "사용자 닉네임 변경", description = "파라미터로 받은 userSeq에 해당하는 사용자의 닉네임을 파라미터 nickname으로 변경하고" +
//            "변경한 닉네임을 반환한다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
//            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
//    })
//    @PutMapping("/nickname")
//    public ResponseEntity<CommonResponse<String>> changeUserNickname(){
//        return new ResponseEntity<>(makeCommonResponse(SUCCESS, userService.changeNickname(nicknameReq)), HttpStatus.OK);
//    }

    //사용자 운동세포 이름 변경

    //사용자 정보 변경 (키, 몸무게, 나이 )

    //선호 운동 수정



    private <T> CommonResponse<T> makeCommonResponse(String message, T data) {
        return CommonResponse.<T>builder()
                .message(message)
                .data(data)
                .build();
    }

}
