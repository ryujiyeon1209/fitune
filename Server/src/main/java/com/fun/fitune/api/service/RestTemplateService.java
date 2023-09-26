package com.fun.fitune.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fun.fitune.api.dto.request.RecommendRequest;
import com.fun.fitune.api.dto.response.RecommendResponse;
import com.fun.fitune.api.dto.response.UserSuperResponse;
import com.fun.fitune.exception.CustomExceptionList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.fun.fitune.api.dto.request.UserInfoRequest;
import com.fun.fitune.api.dto.response.CommonResponse;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RestTemplateService {

    @Value("${django.server.url}")  // application.properties 또는 application.yml에 Django 서버 URL을 설정하세요.
    private String djangoServerUrl;

    public RecommendResponse recommend(RecommendRequest recommendRequest) throws JsonProcessingException {
        // 요청 헤더 설정 (multipart/form-data 요청)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // MultiValueMap으로 요청 본문 구성
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("age", recommendRequest.getAge()); // 필드1 설정
        body.add("weight", recommendRequest.getWeight()); // 필드2 설정
        body.add("height", recommendRequest.getHeight()); // 필드2 설정
        body.add("body_fat_percentage", recommendRequest.getBody_fat_percentage()); // 필드2 설정
        body.add("Resting_BPM", recommendRequest.getResting_BPM()); // 필드2 설정
        body.add("active_BPM", recommendRequest.getActive_BPM()); // 필드2 설정

        // RestTemplate 초기화
        RestTemplate restTemplate = new RestTemplate();

        // 요청 엔터티 생성
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // POST 요청 보내기 및 응답 결과 가져오기
        ResponseEntity<?> responseEntity = restTemplate.exchange(
                djangoServerUrl + "/django/recommend/", HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<>() {});


        // 응답 결과 가져오기
        System.out.println( responseEntity.getBody() );

        LinkedHashMap<String, Object> responseData = (LinkedHashMap<String, Object>) responseEntity.getBody();
//        responseData.put("recommended_exercises", List.of("Dancing", "Swimming", "Table Tennis"));

        // LinkedHashMap에서 "recommended_exercises" 필드의 값을 가져옵니다.
        List<String> recommendedExercises = (List<String>) responseData.get("recommended_exercises");

        // 추천 운동 데이터를 사용하여 RecommendResponse 객체를 생성합니다.
        RecommendResponse response = RecommendResponse.builder()
                .recommendFirst(recommendedExercises.get(0))
                .recommendSecond(recommendedExercises.get(1))
                .recommendThird(recommendedExercises.get(2))
                .build();
        System.out.println(response);

        CommonResponse<RecommendResponse> commonResponse = new CommonResponse<>();

        return response;
//            return (CommonResponse<RecommendResponse>) responseEntity.getBody();
         }
    }
