package com.swietlicki;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    static Map<String, BigDecimal> parseXMLtoHashMap() {

        Map<String, BigDecimal> currencyRatesMap = new HashMap<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("eurofxref-daily.xml");
            NodeList currencyList = doc.getElementsByTagName("Cube");
            for (int i = 0; i < currencyList.getLength(); i++) {
                Node c = currencyList.item(i);
                if(c.getNodeType() == Node.ELEMENT_NODE && !c.hasChildNodes()) {
                    Element currency = (Element) c;
                    String shortCurrencyName = currency.getAttribute("currency");
                    BigDecimal currencyRate = new BigDecimal(currency.getAttribute("rate"));
                    currencyRatesMap.put(shortCurrencyName, currencyRate);
                }
            }
            return currencyRatesMap;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
