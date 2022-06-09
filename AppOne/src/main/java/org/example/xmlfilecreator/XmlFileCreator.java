package org.example.xmlfilecreator;

import org.example.businesscard.BusinessCard;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.List;

public class XmlFileCreator implements FileCreator {

    private static final String XMLFILES = "XMLFiles\\";

    public void createFile(List<BusinessCard> businessCardList, String fileName) throws ParserConfigurationException, TransformerException {
        Document document = createDocument();
        Element cardList = document.createElement("cardList");
        document.appendChild(cardList);

        createFileComponents(document, businessCardList, cardList);

        createXMLFile(document, fileName);
    }

    private void createXMLFile(Document document, String fileName) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(XMLFILES + fileName));
        transformer.transform(domSource, streamResult);
    }

    private Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.newDocument();
    }

    private void createFileComponents(Document document, List<BusinessCard> businessCardList, Element cardList) {
        businessCardList.forEach(businessCard -> {
            Element card = document.createElement("card");
            cardList.appendChild(card);

            Element cardName = document.createElement("name");
            cardName.appendChild(document.createTextNode(businessCard.name()));
            card.appendChild(cardName);

            Element cardSurname = document.createElement("surname");
            cardSurname.appendChild(document.createTextNode(businessCard.surname()));
            card.appendChild(cardSurname);

            Element phone = document.createElement("phone");
            phone.appendChild(document.createTextNode(businessCard.phone()));
            card.appendChild(phone);
        });
    }
}
