package org.example;

import org.example.businesscard.BusinessCard;
import org.example.csvfilesreader.CsvFileReader;
import org.example.xmlfilecreator.XmlFileCreator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String csvFileName;
        String xmlFileName;
        if (args.length == 1) {
            csvFileName = args[0];
            xmlFileName = args[0].substring(0, args[0].lastIndexOf('.')) + ".xml";
        } else if (args.length == 2) {
            csvFileName = args[0];
            xmlFileName = args[1];
        } else {
            throw new IllegalArgumentException("Wrong number of arguments!");
        }

        List<BusinessCard> businessCardList = getBusinessCardList(csvFileName);

        try {
            createXmlFileFromList(xmlFileName, businessCardList);
        } catch (TransformerException | ParserConfigurationException e) {
            throw new RuntimeException("Creating file exception with unexpected exception", e);
        }
    }

    private static void createXmlFileFromList(String xmlFileName, List<BusinessCard> businessCardList) throws ParserConfigurationException, TransformerException {
        XmlFileCreator xmlFileCreator = new XmlFileCreator();
        xmlFileCreator.createFile(businessCardList, xmlFileName);
    }

    private static List<BusinessCard> getBusinessCardList(String csvFileName) {
        CsvFileReader csvFileReader = new CsvFileReader();
        return csvFileReader.read("CSVFiles/" + csvFileName);
    }
}