package com.swietlicki;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ParserTest {

    @Test
    void shouldReturnNotEmptyHashMap() {
        //given
        String testDataURI = "testData/twoElementsToTest.xml";
        //when
        Map<String, BigDecimal> result = Parser.parseXMLtoHashMap(testDataURI);
        //then
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnEmptyHashMap() {
        //given
        String testDataURI = "testData/zeroElementsToTest.xml";
        //when
        Map<String, BigDecimal> result = Parser.parseXMLtoHashMap(testDataURI);
        //then
        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnNullWhenDataFileNotExist() {
        //given
        String testDataURI = "NON-EXISTING DATA FILE";
        //when
        Map<String, BigDecimal> result = Parser.parseXMLtoHashMap(testDataURI);
        //then
        assertNull(result);
    }
}