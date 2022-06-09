package com.example.apptworestapi.restapi.service;


import com.example.apptworestapi.businesscard.BusinessCard;
import com.example.apptworestapi.csvfilesreader.CsvFileReader;
import com.example.apptworestapi.restapi.controller.SortOrder;
import com.example.apptworestapi.restapi.controller.ToFilterInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class BusinessCardService {

    private final CsvFileReader csvFileReader;
    private List<BusinessCard> businessCardList;

    @Autowired
    public BusinessCardService() {
        this.csvFileReader = new CsvFileReader();
    }

    public List<BusinessCard> generateBusinessCardList(String filePath) {
        this.businessCardList = csvFileReader.read(filePath);
        return this.businessCardList;
    }

    public List<BusinessCard> filterListBy(ToFilterInput toFilterInput) {
        return getFilteredBusinessCards(toFilterInput, this.businessCardList);
    }

    protected List<BusinessCard> getFilteredBusinessCards(ToFilterInput toFilterInput, List<BusinessCard> businessCardList) {
        List<BusinessCard> filteredBusinessCards = switch (toFilterInput.columnIdxToFilter()) {
            case 0 -> businessCardList.stream().filter(a -> a.getName().contains(toFilterInput.toFilterCond())).toList();
            case 1 ->
                    businessCardList.stream().filter(a -> a.getSurname().contains(toFilterInput.toFilterCond())).toList();
            case 2 -> businessCardList.stream().filter(a -> a.getPhone().contains(toFilterInput.toFilterCond())).toList();
            default -> businessCardList;
        };

        switch (toFilterInput.columnIdxToSort()) {
            case 0 -> {
                if (toFilterInput.sortOrder().equals(SortOrder.ASCENDING)) {
                    filteredBusinessCards = filteredBusinessCards.stream().sorted(Comparator.comparing(BusinessCard::getName)).toList();
                } else {
                    filteredBusinessCards = filteredBusinessCards.stream().sorted(Comparator.comparing(BusinessCard::getName).reversed()).toList();
                }
            }
            case 1 -> {
                if (toFilterInput.sortOrder().equals(SortOrder.ASCENDING)) {
                    filteredBusinessCards = filteredBusinessCards.stream().sorted(Comparator.comparing(BusinessCard::getSurname)).toList();
                } else {
                    filteredBusinessCards = filteredBusinessCards.stream().sorted(Comparator.comparing(BusinessCard::getSurname).reversed()).toList();
                }
            }
            case 2 -> {
                if (toFilterInput.sortOrder().equals(SortOrder.ASCENDING)) {
                    filteredBusinessCards = filteredBusinessCards.stream().sorted(Comparator.comparing(BusinessCard::getPhone)).toList();
                } else {
                    filteredBusinessCards = filteredBusinessCards.stream().sorted(Comparator.comparing(BusinessCard::getPhone).reversed()).toList();
                }
            }
        }
        return filteredBusinessCards;
    }
}
