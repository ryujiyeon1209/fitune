package com.fun.fitune.api.controller;

import com.fun.fitune.api.dto.request.*;
import com.fun.fitune.api.dto.response.CellResponse;
import com.fun.fitune.api.dto.response.CommonResponse;
import com.fun.fitune.api.dto.response.UserInfoResponse;
import com.fun.fitune.api.dto.response.UserSuperResponse;
import com.fun.fitune.api.service.CellService;
import com.fun.fitune.api.service.UserService;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


@Tag(name="사용자 관리 API", description="사용자 정보 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
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


    // 사용자 생성
    @Operation(summary = "회원가입", description = "사용자를 생성한다.")
    @PostMapping("/create")
    public ResponseEntity<CommonResponse<String>> addUser(@RequestBody UserCreateRequest userCreateRequest){
        System.out.println("사용자 생성");
        return new ResponseEntity<>(
                makeCommonResponse(SUCCESS, userService.insertUser(userCreateRequest)), HttpStatus.OK);
    }


    @Operation(summary = "로그인", description = "로그인한다.")
    @PostMapping("/login")
    public ResponseEntity<CommonResponse<Boolean>> loginUser(@RequestBody UserLoginRequest loginRequest){
        System.out.println("로그인");
        return new ResponseEntity<>(
                makeCommonResponse(SUCCESS, userService.selectUser(loginRequest)), HttpStatus.OK);
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


    //사용자 이름 수정
    @Operation(summary = "사용자 이름 변경", description = "파라미터로 받은 userSeq에 해당하는 사용자의 닉네임을 파라미터 nickname으로 변경하고" +
            "변경한 닉네임을 반환한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PutMapping("/nickname/change")
    public ResponseEntity<CommonResponse<String>> changeNickname (@RequestBody NicknameRequest nicknameReq) {
        return new ResponseEntity<>(makeCommonResponse(SUCCESS, userService.changeNickname(nicknameReq)), HttpStatus.OK);
    }


    //사용자 운동세포 이름 수정
    //cell db에 있는애 수정

    //사용자 정보 수정 (키, 몸무게, 나이 )
    @Operation(summary = "사용자 정보 수정", description = "파라미터로 받은 userSeq에 해당하는 사용자의 키,몸무게, 나이를 파라미터 nickname으로 변경하고" +
            "변경한 닉네임을 반환한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PutMapping("/info/change")
    public ResponseEntity<CommonResponse<UserInfoResponse>> changeUserInfo (@RequestBody UserInfoRequest userInfoReq) {
        return new ResponseEntity<>(makeCommonResponse(SUCCESS, new UserInfoResponse(userService.changeUserInfo(userInfoReq))), HttpStatus.OK);

    }


    @Operation(summary = "사용자의 모든 정보", description = "파라미터로 받은 userSeq에 해당하는 모든 엔티티를 반환한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/super/{email}")
    public ResponseEntity<CommonResponse<UserSuperResponse>> getAllUserInfo (@PathVariable("email") String email){
        return new ResponseEntity<>(makeCommonResponse(SUCCESS, userService.selectAllUserInfo(email)), HttpStatus.OK);
    }


    @Operation(summary = "랜덤 유저 5명 받기", description = "파라미터로 받은 userSeq 제외 나머지 유저들을 랜덤 5명 반환")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/random/{userSeq}")
    public ResponseEntity<List<User>> getRandomMatchList(@PathVariable("userSeq") int userSeq) {
        List<User> randomUsers = userService.findRandomUsers(userSeq);
        return new ResponseEntity<>(randomUsers, HttpStatus.OK);
    }


    @Operation(summary = "세포 이름 수정")
    @PatchMapping("/edit/cell/{userSeq}/{cellName}")
    public ResponseEntity<CommonResponse<String>> modifyCellName(@PathVariable("userSeq") int userSeq, @PathVariable("cellName") String cellName){
        return new ResponseEntity<>(makeCommonResponse(SUCCESS, userService.updateCellName(userSeq, cellName)), HttpStatus.OK);
    }


    @Operation(summary = "키 수정")
    @PatchMapping("/edit/height/{userSeq}/{height}")
    public ResponseEntity<CommonResponse<Integer>> modifyHeight(@PathVariable("userSeq") int userSeq, @PathVariable("height") int height){
        return new ResponseEntity<>(makeCommonResponse(SUCCESS, userService.updateHeight(userSeq, height)), HttpStatus.OK);
    }


    @Operation(summary = "몸무게 수정")
    @PatchMapping("/edit/weight/{userSeq}/{weight}")
    public ResponseEntity<CommonResponse<Integer>> modifyWeight(@PathVariable("userSeq") int userSeq, @PathVariable("weight") int weight){
        return new ResponseEntity<>(makeCommonResponse(SUCCESS, userService.updateWeight(userSeq, weight)), HttpStatus.OK);
    }


    @Operation(summary = "운동 강도 수정")
    @PatchMapping("/edit/tension/{userSeq}/{tension}")
    public ResponseEntity<CommonResponse<Integer>> modifyTension(@PathVariable("userSeq") int userSeq, @PathVariable("tension") int tension){
        return new ResponseEntity<>(makeCommonResponse(SUCCESS, userService.updateTension(userSeq, tension)), HttpStatus.OK);
    }


    @Operation(summary = "선호 운동 수정")
    @PostMapping("edit/prefer/{userSeq}/{preferSeq}")
    public ResponseEntity<CommonResponse<String>> modifyPrefer(@PathVariable("userSeq") int userSeq, @PathVariable("preferSeq") Byte preferSeq){
        return new ResponseEntity<>(makeCommonResponse(SUCCESS, userService.updatePrefer(userSeq, preferSeq)), HttpStatus.OK);
    }


//    @Operation(summary = "안정 심박수 수정")
//    @PostMapping("edit/bpm/{userSeq}/{bpm}")
//    public ResponseEntity<CommonResponse<Integer>> modifyBpm(@PathVariable("userSeq") int userSeq, @PathVariable("bpm") int bpm){
//        return new ResponseEntity<>(makeCommonResponse(SUCCESS, userService.updateBpm(userSeq, bpm)), HttpStatus.OK);
//    }
//
//
//    @Operation(summary = "활동 심박수 수정")
//    @PostMapping("edit/active_bpm/{userSeq}/{bpm}")
//    public ResponseEntity<CommonResponse<Integer>> modifyActiveBpm(@PathVariable("userSeq") int userSeq, @PathVariable("bpm") int bpm){
//        return new ResponseEntity<>(makeCommonResponse(SUCCESS, userService.updateActiveBpm(userSeq, bpm)), HttpStatus.OK);
//    }


    private <T> CommonResponse<T> makeCommonResponse(String message, T data) {
        return CommonResponse.<T>builder()
                .message(message)
                .data(data)
                .build();
    }

}
