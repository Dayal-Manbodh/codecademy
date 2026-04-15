package com.codecademy.test;

import com.codecademy.validation.Validation;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class DateTests {
    private Validation Validation = new Validation();

    @Test
    public void DateTrueTest() {
        Assert.assertTrue(Validation.DateValidation(LocalDate.now()));
    }

    @Test
    public void DateFalseTest() {
        Assert.assertFalse(Validation.DateValidation(LocalDate.now().plusDays(1)));
    }
    
}
