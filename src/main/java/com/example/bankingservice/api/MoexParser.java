package com.example.bankingservice.api;

import com.example.bankingservice.domain.BondDTO;
import com.example.bankingservice.domain.CurrRate;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
@Service
public class MoexParser {

    private final MoexApi moexApi;

    public MoexParser(MoexApi moexApi) {
        this.moexApi = moexApi;
    }


    public List<BondDTO> getAllBonds() {
        List<BondDTO> bonds = new ArrayList<>();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Reader reader = new StringReader(moexApi.getByMoex());
            Document doc = builder.parse(new InputSource(reader));
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("marketdata");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) node;
                    NodeList nodeList = el.getElementsByTagName("rows");
                    for (int j = 0; j < nodeList.getLength(); j++) {
                        Node n = nodeList.item(j);
                        if (n.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) n;
                            String ticker = element.getElementsByTagName("SECID").item(0).getTextContent();
                            Double price = Double.valueOf(element.getElementsByTagName("LAST").item(0).getTextContent());
                            bonds.add(new BondDTO(ticker, price));

                        }

                    }

                }

            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }


        return bonds;
    }

}
