package test;

import Validation.Validation;
import org.junit.Assert;
import org.junit.Test;

public class EmailTests {
    private Validation validation = new Validation();

    @Test
    public void EmailTrueTest() throws Exception {
        Assert.assertTrue(validation.EmailValidation("student@avans.nl"));
    }

    @Test
    public void EmailFalseTest() throws Exception {
        Assert.assertFalse(validation.EmailValidation("student@avans.com"));
        Assert.assertFalse(validation.EmailValidation("student@avans"));
    }
}
