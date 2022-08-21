package com.swietlicki;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyCalculatorTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private CurrencyCalculator underTest;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        underTest = new CurrencyCalculator(prepareTestData());
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    private Map<String, BigDecimal> prepareTestData() {
        Map<String, BigDecimal> data = new HashMap<>();
        data.put("AAA", BigDecimal.valueOf(1.5));
        data.put("BBB", BigDecimal.valueOf(10.000));
        data.put("CCC", BigDecimal.valueOf(0.123));
        return data;
    };

    @Test
    void constructorTestWhenNoData() {
        assertThrows(IllegalArgumentException.class, ()-> new CurrencyCalculator(null));
    }

    @Test
    void shouldCalculateCurrency() {
        //given
        BigDecimal testAmount = BigDecimal.valueOf(10);
        String testCurrency = "AAA";
        //when
        BigDecimal result = underTest.calculateCurrency(testAmount, testCurrency);
        //then
        assertEquals("--> RESULT: 10 EUR = 15.0 AAA\r\n",
                outputStreamCaptor.toString());
        assertEquals(BigDecimal.valueOf(15).stripTrailingZeros(), result.stripTrailingZeros());
    }

    @Test
    void shouldReturnNullAsResult() {
        //given
        BigDecimal testAmount = null;
        String testCurrency = "AAA";
        //when
        BigDecimal result = underTest.calculateCurrency(testAmount, testCurrency);
        //then
        assertEquals("Please enter the correct data\r\n",
                outputStreamCaptor.toString());
        assertNull(result);
    }

    @Test
    void shouldPrintAvailableCurrency() {
        //when
        underTest.printAvailableCurrency();
        //then
        assertTrue(outputStreamCaptor.toString().contains("AAA"));
        assertTrue(outputStreamCaptor.toString().contains("CCC"));
        assertTrue(outputStreamCaptor.toString().contains("BBB"));
    }

    @Test
    void shouldGetCurrencyAbbreviation() {
        //given
        String userInput = "AAA";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        //when
        String result = underTest.getCurrencyAbbreviationFromUser();
        //then
        assertEquals("-> Type the 3-letter abbreviation of the destination currency (e.g. PLN): ",
                outputStreamCaptor.toString());
        assertEquals(userInput, result);

    }

    @Test
    void shouldNotGetCurrencyAbbreviation() {
        //given
        String userInput = "This is not a currency abbreviation";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        //when
        String result = underTest.getCurrencyAbbreviationFromUser();
        //then
        assertEquals("-> Type the 3-letter abbreviation of the destination currency (e.g. PLN): "
                        + "Incorrect currency code. See the list of available currencies in the main menu.\r\n",
                outputStreamCaptor.toString());
        assertNull(result);

    }

    @Test
    void shouldGetEuroAmountFromUser() {
        //given
        String userInput = "123";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        //when
        BigDecimal result = underTest.getEuroAmountFromUser();
        //then
        assertEquals("-> Type the amount from EUR to convert into another currency (e.g. 71.23): ",
                outputStreamCaptor.toString());
        assertEquals(BigDecimal.valueOf(123), result);
    }

    @Test
    void shouldNotGetEuroAmountFromUserWhenNegative() {
        //given
        String userInput = "-123";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        //when
        BigDecimal result = underTest.getEuroAmountFromUser();
        //then
        assertEquals("-> Type the amount from EUR to convert into another currency (e.g. 71.23): " +
                        "The amount must be grater than zero!\r\n",
                outputStreamCaptor.toString());
        assertNull(result);

    }

    @Test
    void shouldThrowWhenGettingEuroAmountFromUser() {
        //given
        String userInput = "test";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        //then
        BigDecimal result = underTest.getEuroAmountFromUser();
        assertEquals("-> Type the amount from EUR to convert into another currency (e.g. 71.23): "
                        + "Incorrect EUR amount. Example of acceptable format: 10.05\r\n",
                outputStreamCaptor.toString());
        assertNull(result);
    }
}