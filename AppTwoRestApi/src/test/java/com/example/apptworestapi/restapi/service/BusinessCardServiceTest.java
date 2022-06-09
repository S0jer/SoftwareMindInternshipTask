package com.example.apptworestapi.restapi.service;

import com.example.apptworestapi.businesscard.BusinessCard;
import com.example.apptworestapi.csvfilesreader.CsvFileReader;
import com.example.apptworestapi.restapi.controller.SortOrder;
import com.example.apptworestapi.restapi.controller.ToFilterInput;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BusinessCardServiceTest {


    @ParameterizedTest
    @MethodSource("filePathAndAscendingSortedListProvider")
    void shouldTestAscendingSortingWithoutFilters(String filePath, List<BusinessCard> expectedList, Integer columnIdx,
                                                  Boolean expectedResult) {
        BusinessCardService businessCardService = new BusinessCardService();
        CsvFileReader csvFileReader = csvFileReaderCreator();
        ToFilterInput toFilterInput = new ToFilterInput("", -1, columnIdx, SortOrder.ASCENDING);

        List<BusinessCard> cardsList = csvFileReader.read(filePath);
        List<BusinessCard> actualList = businessCardService.getFilteredBusinessCards(toFilterInput, cardsList);

        assertEquals(expectedResult, actualList.equals(expectedList));
    }


    @ParameterizedTest
    @MethodSource("filePathAndAscendingSortedListProvider")
    void shouldTestDescendingSortingWithoutFilters(String filePath, List<BusinessCard> expectedList, Integer columnIdx,
                                                   Boolean expectedResult) {
        BusinessCardService businessCardService = new BusinessCardService();
        Collections.reverse(expectedList);
        CsvFileReader csvFileReader = csvFileReaderCreator();
        ToFilterInput toFilterInput = new ToFilterInput("", -1, columnIdx, SortOrder.DESCENDING);

        List<BusinessCard> cardsList = csvFileReader.read(filePath);
        List<BusinessCard> actualList = businessCardService.getFilteredBusinessCards(toFilterInput, cardsList);

        assertEquals(expectedResult, actualList.equals(expectedList));
    }

    @ParameterizedTest
    @MethodSource("filePathAndAscendingSortedListWithFilterProvider")
    void shouldTestAscendingSortingWithFilter(String filePath, List<BusinessCard> expectedList, Integer columnIdx,
                                              String toFilterCond, Boolean expectedResult) {
        BusinessCardService businessCardService = new BusinessCardService();
        CsvFileReader csvFileReader = csvFileReaderCreator();
        ToFilterInput toFilterInput = new ToFilterInput(toFilterCond, columnIdx, columnIdx, SortOrder.ASCENDING);

        List<BusinessCard> cardsList = csvFileReader.read(filePath);
        List<BusinessCard> actualList = businessCardService.getFilteredBusinessCards(toFilterInput, cardsList);

        assertEquals(expectedResult, actualList.equals(expectedList));
    }

    @ParameterizedTest
    @MethodSource("filePathAndAscendingSortedListWithFilterProvider")
    void shouldTestDescendingSortingWithFilter(String filePath, List<BusinessCard> expectedList, Integer columnIdx,
                                               String toFilterCond, Boolean expectedResult) {
        BusinessCardService businessCardService = new BusinessCardService();
        Collections.reverse(expectedList);
        CsvFileReader csvFileReader = csvFileReaderCreator();
        ToFilterInput toFilterInput = new ToFilterInput(toFilterCond, columnIdx, columnIdx, SortOrder.DESCENDING);

        List<BusinessCard> cardsList = csvFileReader.read(filePath);
        List<BusinessCard> actualList = businessCardService.getFilteredBusinessCards(toFilterInput, cardsList);

        assertEquals(expectedResult, actualList.equals(expectedList));
    }


    @ParameterizedTest
    @MethodSource("filePathAndListWithFilterProvider")
    void shouldTestOnlyWithFilter(String filePath, List<BusinessCard> expectedList, Integer columnIdx,
                                  String toFilterCond, Boolean expectedResult) {
        BusinessCardService businessCardService = new BusinessCardService();
        CsvFileReader csvFileReader = csvFileReaderCreator();
        ToFilterInput toFilterInput = new ToFilterInput(toFilterCond, columnIdx, -1, SortOrder.ASCENDING);

        List<BusinessCard> cardsList = csvFileReader.read(filePath);
        List<BusinessCard> actualList = businessCardService.getFilteredBusinessCards(toFilterInput, cardsList);

        assertEquals(expectedResult, actualList.equals(expectedList));
    }


    static Stream<Arguments> filePathAndAscendingSortedListProvider() {
        String filePath = "CSVFiles/exampleTwoTest.csv";
        List<List<BusinessCard>> expectedCardsList = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(
                        new BusinessCard("Aishah", "Bate", "035815400401"),
                        new BusinessCard("Bentley", "Shah", "380904541665"),
                        new BusinessCard("Eilidh", "Hester", "920924460987"),
                        new BusinessCard("Margot", "Begum", "997106174917"),
                        new BusinessCard("Zaydan", "Mccray", "959883197105")
                )),
                new ArrayList<>(Arrays.asList(
                        new BusinessCard("Aishah", "Bate", "035815400401"),
                        new BusinessCard("Margot", "Begum", "997106174917"),
                        new BusinessCard("Eilidh", "Hester", "920924460987"),
                        new BusinessCard("Zaydan", "Mccray", "959883197105"),
                        new BusinessCard("Bentley", "Shah", "380904541665")
                )),
                new ArrayList<>(Arrays.asList(
                        new BusinessCard("Aishah", "Bate", "035815400401"),
                        new BusinessCard("Bentley", "Shah", "380904541665"),
                        new BusinessCard("Eilidh", "Hester", "920924460987"),
                        new BusinessCard("Zaydan", "Mccray", "959883197105"),
                        new BusinessCard("Margot", "Begum", "997106174917")
                ))));

        return Stream.of(arguments(filePath, expectedCardsList.get(0), 0, true),
                arguments(filePath, expectedCardsList.get(1), 1, true),
                arguments(filePath, expectedCardsList.get(2), 2, true),
                arguments(filePath, expectedCardsList.get(1), 2, false),
                arguments(filePath, expectedCardsList.get(0), 2, false),
                arguments(filePath, expectedCardsList.get(1), 0, false));
    }

    static Stream<Arguments> filePathAndAscendingSortedListWithFilterProvider() {
        String filePath = "CSVFiles/exampleTwoTest.csv";
        List<List<BusinessCard>> expectedCardsList = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(
                        new BusinessCard("Aishah", "Bate", "035815400401"),
                        new BusinessCard("Margot", "Begum", "997106174917"),
                        new BusinessCard("Zaydan", "Mccray", "959883197105")
                )),
                new ArrayList<>(Arrays.asList(
                        new BusinessCard("Aishah", "Bate", "035815400401"),
                        new BusinessCard("Margot", "Begum", "997106174917"),
                        new BusinessCard("Eilidh", "Hester", "920924460987")
                )),
                new ArrayList<>(Arrays.asList(
                        new BusinessCard("Bentley", "Shah", "380904541665"),
                        new BusinessCard("Eilidh", "Hester", "920924460987"),
                        new BusinessCard("Zaydan", "Mccray", "959883197105"),
                        new BusinessCard("Margot", "Begum", "997106174917")
                ))));

        return Stream.of(arguments(filePath, expectedCardsList.get(0), 0, "a", true),
                arguments(filePath, expectedCardsList.get(1), 1, "e", true),
                arguments(filePath, expectedCardsList.get(2), 2, "9", true),
                arguments(filePath, expectedCardsList.get(2), 0, "a", false),
                arguments(filePath, expectedCardsList.get(0), 2, "e", false),
                arguments(filePath, expectedCardsList.get(1), 1, "H", false));
    }

    static Stream<Arguments> filePathAndListWithFilterProvider() {
        String filePath = "CSVFiles/exampleTwoTest.csv";
        List<List<BusinessCard>> expectedCardsList = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(
                        new BusinessCard("Aishah", "Bate", "035815400401"),
                        new BusinessCard("Eilidh", "Hester", "920924460987")
                )),
                new ArrayList<>(Arrays.asList(
                        new BusinessCard("Zaydan", "Mccray", "959883197105"),
                        new BusinessCard("Eilidh", "Hester", "920924460987")
                )),
                new ArrayList<>(Arrays.asList(
                        new BusinessCard("Bentley", "Shah", "380904541665"),
                        new BusinessCard("Aishah", "Bate", "035815400401"),
                        new BusinessCard("Zaydan", "Mccray", "959883197105"),
                        new BusinessCard("Eilidh", "Hester", "920924460987"),
                        new BusinessCard("Margot", "Begum", "997106174917")
                ))));

        return Stream.of(arguments(filePath, expectedCardsList.get(0), 0, "h", true),
                arguments(filePath, expectedCardsList.get(1), 1, "r", true),
                arguments(filePath, expectedCardsList.get(2), 2, "0", true),
                arguments(filePath, expectedCardsList.get(2), 0, "a", false),
                arguments(filePath, expectedCardsList.get(0), 2, "c", false),
                arguments(filePath, expectedCardsList.get(1), 1, "H", false));
    }

    private CsvFileReader csvFileReaderCreator() {
        return new CsvFileReader();
    }
}