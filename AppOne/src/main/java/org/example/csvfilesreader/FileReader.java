package org.example.csvfilesreader;

import org.example.businesscard.BusinessCard;

import java.util.List;

@FunctionalInterface
public interface FileReader {
    List<BusinessCard> read(String filePath);
}
