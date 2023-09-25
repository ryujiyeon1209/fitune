package com.fun.fitune.api.controller;

import com.fun.fitune.api.dto.response.CommonResponse;
import com.fun.fitune.api.dto.response.UserInfoResponse;
import com.fun.fitune.api.service.RestTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/django")
public class ApiController {
    private final RestTemplateService templateService;

    public ApiController(RestTemplateService templateService) {
        this.templateService = templateService;
    }


//    @GetMapping("/recommend")
//    public CommonResponse<List> postHello() {
//        return templateService.recommend();
//    }
}
