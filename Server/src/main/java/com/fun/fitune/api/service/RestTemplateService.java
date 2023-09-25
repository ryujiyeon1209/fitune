package com.fun.fitune.api.service;

import com.fun.fitune.api.dto.request.UserInfoRequest;
import com.fun.fitune.api.dto.response.CommonResponse;
import com.fun.fitune.api.dto.response.UserInfoResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class RestTemplateService {

//    public CommonResponse<List> recommend(){
//
//        URI uri = UriComponentsBuilder
//                .fromUriString("http://localhost:8000")
//                .path("/django/recommend/")
//                .encode()
//                .build()
//                .expand(UserInfoRequest)
//                .toUri();
//    }
}
