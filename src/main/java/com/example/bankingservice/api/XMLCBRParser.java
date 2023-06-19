package com.example.bankingservice.api;

import com.example.bankingservice.domain.CurrRate;
import com.example.bankingservice.domain.Currency;
import com.sun.istack.NotNull;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class XMLCBRParser {

    private final CBRApi currencyApi;


    public XMLCBRParser(CBRApi currencyApi) {
        this.currencyApi = currencyApi;
    }


    public List<CurrRate> getAllRates() {
        List<CurrRate> rates = new ArrayList<CurrRate>();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Reader reader = new StringReader(currencyApi.getRatesByCbr());
            Document doc = builder.parse(new InputSource(reader));
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("Valute");
            for (int i=0;i< list.getLength();i++) {
                Node node = list.item(i);
                if (node.getNodeType()==Node.ELEMENT_NODE){
                    Element el = (Element) node;
                    String numCode = el.getElementsByTagName("NumCode").item(0).getTextContent();
                    String charCode = el.getElementsByTagName("CharCode").item(0).getTextContent();
                    String nominal = el.getElementsByTagName("Nominal").item(0).getTextContent();
                    String name = el.getElementsByTagName("Name").item(0).getTextContent();
                    Double value = Double.valueOf(el.getElementsByTagName("Value").item(0).getTextContent().replace(",","."));
                    rates.add(new CurrRate(numCode,charCode,nominal,name,value));
                }

            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }


        return rates;
    }




    public Map<Currency, BigDecimal> getMapRates(@NotNull List<CurrRate> rates) {
        Map<Currency,BigDecimal> mapRates = rates.stream()
                .collect(Collectors.toMap(el->Currency.valueOf(el.getCharCode()), el->BigDecimal.valueOf(el.getValue())));
        return mapRates;
    }
}
