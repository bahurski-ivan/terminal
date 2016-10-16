package ru.sbrf;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ivan on 16/10/16.
 */
public class PinValidatorTest {
    private static final String CORRECT_PIN = "1234";


    @Test
    public void isValidSimple() throws Exception {
        PinValidator validator = new PinValidator(CORRECT_PIN);

        Assert.assertFalse(validator.isValid("1111"));
        Assert.assertTrue(validator.isValid("1234"));
    }

    @Test(expected = AccountIsLockedException.class)
    public void isValidThrow() throws Exception {
        PinValidator validator = new PinValidator(CORRECT_PIN);

        Assert.assertFalse(validator.isValid("1111"));
        Assert.assertFalse(validator.isValid("0000"));
        Assert.assertFalse(validator.isValid("1010"));

        validator.isValid("1234");
    }

    @Test(expected = NotAuthenticatedException.class)
    public void ensureAuthNotAuthException() throws Exception {
        PinValidator validator = new PinValidator(CORRECT_PIN);
        validator.ensureAuth();
    }

    @Test(expected = AccountIsLockedException.class)
    public void ensureAuthAccountIsLockedException() throws Exception {
        PinValidator validator = new PinValidator(CORRECT_PIN);

        Assert.assertFalse(validator.isValid("1111"));
        Assert.assertFalse(validator.isValid("0000"));
        Assert.assertFalse(validator.isValid("1010"));

        validator.ensureAuth();
    }

    @Test
    public void ensureAuthAuthenticated() throws Exception {
        PinValidator validator = new PinValidator(CORRECT_PIN);
        validator.isValid(CORRECT_PIN);
        validator.ensureAuth();
    }

    @Test(expected = AccountIsLockedException.class)
    public void throwOnLockTrue() throws Exception {
        PinValidator validator = new PinValidator(CORRECT_PIN);

        Assert.assertFalse(validator.isValid("1111"));
        Assert.assertFalse(validator.isValid("0000"));
        Assert.assertFalse(validator.isValid("1010"));

        validator.throwOnLock();
    }

    @Test
    public void throwOnLockAuthenticated() throws Exception {
        PinValidator validator = new PinValidator(CORRECT_PIN);
        validator.isValid(CORRECT_PIN);
        validator.throwOnLock();
    }

    @Test
    public void isLockedTrue() throws Exception {
        PinValidator validator = new PinValidator(CORRECT_PIN);

        Assert.assertFalse(validator.isValid("1111"));
        Assert.assertFalse(validator.isValid("0000"));
        Assert.assertFalse(validator.isValid("1010"));

        Assert.assertTrue(validator.isLocked());
    }

    @Test
    public void isLockedNotAuth() throws Exception {
        PinValidator validator = new PinValidator(CORRECT_PIN);
        Assert.assertFalse(validator.isLocked());
    }

    @Test
    public void isLockedAuthenticated() throws Exception {
        PinValidator validator = new PinValidator(CORRECT_PIN);
        validator.isValid(CORRECT_PIN);
        Assert.assertFalse(validator.isLocked());
    }

}