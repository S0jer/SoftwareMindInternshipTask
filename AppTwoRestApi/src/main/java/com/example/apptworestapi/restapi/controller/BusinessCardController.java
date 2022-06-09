package com.example.apptworestapi.restapi.controller;


import com.example.apptworestapi.businesscard.BusinessCard;
import com.example.apptworestapi.restapi.service.BusinessCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusinessCardController {

    private final BusinessCardService businessCardService;
    private static final String BASIC_FILE_PATH = "CSVFiles/example.csv";


    @Autowired
    public BusinessCardController(BusinessCardService businessCardService) {
        this.businessCardService = businessCardService;
        this.businessCardService.generateBusinessCardList(BASIC_FILE_PATH);
    }

    @PostMapping("/filepath")
    public List<BusinessCard> generateBusinessCardList(@RequestParam String filePath) {
        return this.businessCardService.generateBusinessCardList(filePath);
    }

    @GetMapping("/cards")
    public List<BusinessCard> filterListBy(@RequestParam(defaultValue = "") String toFilterCond,
                                           @RequestParam(defaultValue = "-1") Integer columnIdxToFilter,
                                           @RequestParam(defaultValue = "-1") Integer columnIdxToSort,
                                           @RequestParam(defaultValue = "ASCENDING") SortOrder sortOrder) {
        ToFilterInput toFilterInput = new ToFilterInput(toFilterCond, columnIdxToFilter, columnIdxToSort, sortOrder);
        return this.businessCardService.filterListBy(toFilterInput);
    }
}
