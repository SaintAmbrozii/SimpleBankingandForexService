package com.example.bankingservice;

import com.example.bankingservice.api.MoexApi;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class Test {


    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        File file = new File("https://iss.moex.com/iss/engines/stock/markets/shares/boards/TQBR/securities.xml?iss.meta=off&iss.only=marketdata&marketdata.columns=SECID,LAST");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document = documentBuilderFactory.newDocumentBuilder().parse(file);

        Element first = (Element) document.getElementsByTagName("marketdata");
        System.out.println(first);

    }




}
