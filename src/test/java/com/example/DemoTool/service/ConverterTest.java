package com.example.DemoTool.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xmlunit.util.Convert;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    Converter converter = new Converter();

    @Test
    void convertDateToLongWrong() {
        Assertions.assertThrows(ParseException.class, () -> {
            converter.convertDateToLong("raf");
        });
    }

    @Test
    void convertDateToLongTrue() throws ParseException {
        String stringDate = "2006-05-21";
        Assertions.assertEquals(converter.convertDateToLong(stringDate), 1148162400000L);
    }

    @Test
    void convertTimeToLongWrong() {
        Assertions.assertThrows(ParseException.class, () -> {
            converter.convertTimeToLong("raf");
        });
    }

    @Test
    void convertTimeToLongTrue() throws ParseException {
        String stringTime = "02:12";
        Assertions.assertEquals(converter.convertTimeToLong(stringTime), 7920000);
    }

    @Test
    void convertLongToStringDateTrue() {
        long millisTime = 1148162400000L;
        Assertions.assertEquals(converter.convertLongToStringDate(millisTime), "21/05/2006 - 00:00");
    }
}