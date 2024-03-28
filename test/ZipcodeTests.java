package test;

import Validation.Validation;
import org.junit.Assert;
import org.junit.Test;

public class ZipcodeTests {
    private Validation validation = new Validation();

    @Test
    public void ZipcodeTrueTest() throws Exception {
        Assert.assertTrue(validation.ZipcodeValidation("1234 AB"));
    }

    @Test
    public void ZipcodeStartsWithLetter() throws Exception {
        Assert.assertFalse(validation.ZipcodeValidation("ABCD 12"));
    }

    @Test
    public void ZipcodeStartsWithZero() throws Exception {
        Assert.assertFalse(validation.ZipcodeValidation("0123 AB"));
    }

    @Test
    public void ZipcodeWithNoSpaces() throws Exception {
        Assert.assertFalse(validation.ZipcodeValidation("1234AB"));
    }

    @Test
    public void ZipcodeWithNoUpperCase() throws Exception {
        Assert.assertFalse(validation.ZipcodeValidation("1234 ab"));
    }
}
