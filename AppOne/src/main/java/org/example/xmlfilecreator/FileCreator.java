package org.example.xmlfilecreator;

import org.example.businesscard.BusinessCard;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

@FunctionalInterface
public interface FileCreator {
    void createFile(List<BusinessCard> businessCardList, String fileName) throws ParserConfigurationException, TransformerException;
}
