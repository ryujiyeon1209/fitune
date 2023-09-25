package com.fun.fitune.api.controller;

import com.fun.fitune.api.dto.response.UserInfoResponse;
import com.fun.fitune.api.service.RestTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/django")
public class ApiController {
    private final RestTemplateService templateService;

    public ApiController(RestTemplateService templateService) {
        this.templateService = templateService;
    }

//
//    @GetMapping("/recommend")
//    public UserInfoResponse postHello() {
//        return templateService.recommend();
//    }
}
