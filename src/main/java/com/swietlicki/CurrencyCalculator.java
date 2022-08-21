package com.swietlicki;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class CurrencyCalculator {

    private final Map<String, BigDecimal> currenciesAndRates;

    public CurrencyCalculator(Map<String, BigDecimal> currenciesAndRates) {
        if(currenciesAndRates == null) {
            System.out.println("");
            throw new IllegalArgumentException("No currency data available!");
        }
        this.currenciesAndRates = currenciesAndRates;
    }

    public void mainMenu() {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("\n*** SELECT OPTION [type number and press enter] : ***\n"
                    + "1. Convert Euros to another currency \n"
                    + "2. See list of available destination currencies \n"
                    + "0. Exit");
            switch (sc.nextInt()) {
                case 1:
                    calculateCurrency(getEuroAmountFromUser(), getCurrencyAbbreviationFromUser());
                    break;
                case 2:
                    printAvailableCurrency();
                    break;
                case 0:
                    sc.close();
                    flag = false;
                    break;
                default:
                    System.out.println("Incorrect data. Enter one of the above option numbers and press enter");
                    break;
            }
        }
    }

    public BigDecimal calculateCurrency(BigDecimal amount, String currencyNameCode) {
        if(amount != null && currencyNameCode != null) {
            BigDecimal currencyRate = currenciesAndRates.get(currencyNameCode);
            BigDecimal result = currencyRate.multiply(amount);
            System.out.println("--> RESULT: " + amount + " EUR = " + result
                    + " " + currencyNameCode);
            return result;
        } else {
            System.out.println("Please enter the correct data");
            return null;
        }
    }

    public void printAvailableCurrency() {
        System.out.println("--- Available currencies for conversion from EUR: ---");
        int i=1;
        for (String key : currenciesAndRates.keySet()) {
            System.out.println(i++ + ") " + key);
        }
    }

    public String getCurrencyAbbreviationFromUser() {
        System.out.print("-> Type the 3-letter abbreviation of the destination currency (e.g. PLN): ");
        String input = new Scanner(System.in).nextLine().toUpperCase();
        if (currenciesAndRates.containsKey(input)) {
            return input;
        } else {
            System.out.println("Incorrect currency code. See the list of available currencies in the main menu.");
            return null;
        }
    }

    public BigDecimal getEuroAmountFromUser() {
        System.out.print("-> Type the amount from EUR to convert into another currency (e.g. 71.23): ");

        String input = new Scanner(System.in).nextLine().replace("," ,".");
        try {
            BigDecimal amount = new BigDecimal(input);
            if(amount.compareTo(BigDecimal.ZERO) > 0) {
                return amount;
            } else {
                System.out.println("The amount must be grater than zero!");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Incorrect EUR amount. Example of acceptable format: 10.05");
        }
        return null;
    }
}
