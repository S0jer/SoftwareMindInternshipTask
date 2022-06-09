package org.example.xmlfilecreator;

import org.example.businesscard.BusinessCard;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class XmlFileCreatorTest {

    private static final String XMLFILES = "XMLFiles/testResult.xml";
    private static final String XMLFILENAME = "testResult.xml";

    @ParameterizedTest
    @MethodSource("filePathsAndResultsListProvider")
    void shouldTestCreatorOfXmlFiles(List<BusinessCard> cardList, String filePathsOfResult, boolean expectedResult) throws ParserConfigurationException, TransformerException, IOException {
        File fileExpected = new File(filePathsOfResult);
        XmlFileCreator xmlFileCreator = new XmlFileCreator();

        xmlFileCreator.createFile(cardList, XMLFILENAME);
        File actualFile = new File(XMLFILES);

        assertEquals(expectedResult, filesCompareByLine(fileExpected, actualFile) == -1L);
    }

    private long filesCompareByLine(File expectedFile, File actualFile) throws IOException {
        BufferedReader bf1 = new BufferedReader(new InputStreamReader(new FileInputStream(expectedFile), StandardCharsets.UTF_8));
        BufferedReader bf2 = new BufferedReader(new InputStreamReader(new FileInputStream(actualFile), StandardCharsets.UTF_8));

        long lineNumber = 1;
        String line1, line2;
        while ((line1 = bf1.readLine()) != null) {
            line2 = bf2.readLine();
            if (!line1.equals(line2)) {
                return lineNumber;
            }
            lineNumber++;
        }
        if (bf2.readLine() == null) {
            return -1;
        } else {
            return lineNumber;
        }
    }


    static Stream<Arguments> filePathsAndResultsListProvider() {
        String[] filePathsOfResults = {"XMLFiles/exampleTwoTestExpectedResult.xml", "XMLFiles/exampleOneTestExpectedResult.xml", "XMLFiles/exampleTestExpectedResult.xml"};
        List<List<BusinessCard>> exampleCardsList = new ArrayList<>(Arrays.asList(
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


        return Stream.of(arguments(exampleCardsList.get(0), filePathsOfResults[0], true),
                arguments(exampleCardsList.get(1), filePathsOfResults[0], false),
                arguments(exampleCardsList.get(1), filePathsOfResults[1], true),
                arguments(exampleCardsList.get(0), filePathsOfResults[2], false),
                arguments(exampleCardsList.get(2), filePathsOfResults[1], false),
                arguments(exampleCardsList.get(2), filePathsOfResults[2], true)
        );
    }
}
