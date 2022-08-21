package com.swietlicki;

import java.math.BigDecimal;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<String, BigDecimal> currenciesWithRates = Parser.parseXMLtoHashMap();
        CurrencyCalculator calculator = new CurrencyCalculator(currenciesWithRates);
        calculator.mainMenu();
    }
}
