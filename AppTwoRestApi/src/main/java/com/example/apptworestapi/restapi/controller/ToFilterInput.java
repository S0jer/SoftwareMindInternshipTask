package com.example.apptworestapi.restapi.controller;


public record ToFilterInput(String toFilterCond, Integer columnIdxToFilter, Integer columnIdxToSort,
                            SortOrder sortOrder) {

}
