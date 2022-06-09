package com.example.apptworestapi.csvfilesreader;

import com.example.apptworestapi.businesscard.BusinessCard;

import java.util.List;

@FunctionalInterface
public interface FileReader {
    List<BusinessCard> read(String filePath);
}
