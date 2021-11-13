package com.emse.spring.faircorp.controller;

import com.emse.spring.faircorp.dto.BuildingDto;
import com.emse.spring.faircorp.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/faircorp")
@Transactional
public class InitController {

    @Autowired
    InitService initService;

    @GetMapping("/init")
    public void findAll() {
        initService.init();
    }
}
