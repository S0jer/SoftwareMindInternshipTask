package com.example.apptworestapi.csvfilesreader;

import com.example.apptworestapi.businesscard.BusinessCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CsvFileReaderTest {

    @ParameterizedTest
    @MethodSource("filePathsAndResultsListProvider")
    void shouldCreateCorrectListOfCards(String filePath, List<BusinessCard> expectedList, Boolean expectedResult) {
        CsvFileReader csvFileReader = csvFileReaderCreator();

        List<BusinessCard> actualList = csvFileReader.read(filePath);

        assertEquals(expectedResult, actualList.equals(expectedList));
    }


    @Test
    void shouldReturnWrongPathException() {
        CsvFileReader csvFileReader = csvFileReaderCreator();
        String expectedMessage = "Wrong path to file";

        Exception exception = assertThrows(CsvFileReader.WrongPathException.class, () -> csvFileReader.read(""));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldTestFileWithEmptyLine() {
        CsvFileReader csvFileReader = csvFileReaderCreator();
        String fileWithEmptyLine = "CSVFiles/exampleTestEmptyLine.csv";
        String expectedMessage = "Wrong argument in line 5 and column 0";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> csvFileReader.read(fileWithEmptyLine));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldTestFileWithEmptyColumn() {
        CsvFileReader csvFileReader = csvFileReaderCreator();
        String fileWithEmptyColumn = "CSVFiles/exampleTestEmptyColumn.csv";
        String expectedMessage = "Wrong argument in line 4 and column 1";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> csvFileReader.read(fileWithEmptyColumn));
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);

        assertTrue(actualMessage.contains(expectedMessage));
    }

    static Stream<Arguments> filePathsAndResultsListProvider() {
        String[] filePaths = {"CSVFiles/exampleTwoTest.csv", "CSVFiles/exampleOneTest.csv", "CSVFiles/example.csv"};
        List<List<BusinessCard>> expectedCardsList = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(
                        new BusinessCard("Bentley", "Shah", "380904541665"),
                        new BusinessCard("Aishah", "Bate", "035815400401"),
                        new BusinessCard("Zaydan", "Mccray", "959883197105"),
                        new BusinessCard("Eilidh", "Hester", "920924460987"),
                        new BusinessCard("Margot", "Begum", "997106174917")
                )),
                new ArrayList<>(Arrays.asList(
                        new BusinessCard("Avery", "Laing", "159898103470"),
                        new BusinessCard("Amba", "Morrow", "495225809670"),
                        new BusinessCard("Sohaib", "Alvarado", "391930469997"),
                        new BusinessCard("Sion", "Mooney", "114412226393"),
                        new BusinessCard("Letitia", "Stephenson", "621149851947"),
                        new BusinessCard("Meerab", "York", "248376164170"),
                        new BusinessCard("Paloma", "Burgess", "939520400109"),
                        new BusinessCard("Shanna", "Finch", "368469629557")
                )),
                new ArrayList<>(Arrays.asList(
                        new BusinessCard("Lonnie", "Collins", "14503758742"),
                        new BusinessCard("Gordon", "Thompson", "922970267410"),
                        new BusinessCard("Vanessa", "Shaw", "567324560123"),
                        new BusinessCard("Aaron", "James", "876315751796"),
                        new BusinessCard("Avery", "Laing", "159898103470"),
                        new BusinessCard("Amba", "Morrow", "495225809670"),
                        new BusinessCard("Sohaib", "Alvarado", "391930469997"),
                        new BusinessCard("Sion", "Mooney", "114412226393"),
                        new BusinessCard("Letitia", "Stephenson", "621149851947"),
                        new BusinessCard("Meerab", "York", "248376164170"),
                        new BusinessCard("Paloma", "Burgess", "939520400109"),
                        new BusinessCard("Shanna", "Finch", "368469629557"),
                        new BusinessCard("Leanne", "Blundell", "270835621178"),
                        new BusinessCard("Athena", "Solomon", "919785543957"),
                        new BusinessCard("Fenton", "Buchanan", "168428805320"),
                        new BusinessCard("Diego", "Prince", "689588471096"),
                        new BusinessCard("Bentley", "Shah", "380904541665"),
                        new BusinessCard("Aishah", "Bate", "035815400401"),
                        new BusinessCard("Zaydan", "Mccray", "959883197105"),
                        new BusinessCard("Eilidh", "Hester", "920924460987"),
                        new BusinessCard("Margot", "Begum", "997106174917"),
                        new BusinessCard("Demi", "Mann", "085563545876"),
                        new BusinessCard("Brittney", "Frame", "725400221544"),
                        new BusinessCard("Yasin", "Milner", "788489586877")
                ))
        ));


        return Stream.of(arguments(filePaths[0], expectedCardsList.get(0), true),
                arguments(filePaths[0], expectedCardsList.get(1), false),
                arguments(filePaths[1], expectedCardsList.get(1), true),
                arguments(filePaths[1], expectedCardsList.get(0), false),
                arguments(filePaths[1], expectedCardsList.get(2), false),
                arguments(filePaths[2], expectedCardsList.get(2), true),
                arguments(filePaths[0], expectedCardsList.get(2), false),
                arguments(filePaths[2], expectedCardsList.get(0), false)
        );
    }

    private CsvFileReader csvFileReaderCreator() {
        return new CsvFileReader();
    }
}