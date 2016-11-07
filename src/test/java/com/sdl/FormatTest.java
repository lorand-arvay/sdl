package com.sdl;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FormatTest {


    @Test
    public void testSinglePhrase() {
        Format format = new Format();
        FormatMessage fm = new FormatMessage("PP 2 2 l' homme $ the man & blah");
        Assert.assertEquals("2 l' homme $ 2 the man & blah |", format.format(fm).getText());
    }

    @Test
    public void testSinglePhraseWithoutOptional() {
        Format format = new Format();
        FormatMessage fm = new FormatMessage("PP 2 2 l' homme $ the man");
        Assert.assertEquals("2 l' homme $ 2 the man |", format.format(fm).getText());
    }

    @Test
    public void testMultiplePhrases() {
        Format format = new Format();
        FormatMessage fm = new FormatMessage("PP 2 2 l' homme $ the man & blah PP 2 2 the woman $ la femme & lorem");
        Assert.assertEquals("2 l' homme $ 2 the man & blah | 2 the woman $ 2 la femme & lorem |", format.format(fm).getText());
    }

    @Test(expected = MessageFormatException.class)
    public void testMissingData() {
        Format format = new Format();
        FormatMessage fm = new FormatMessage("PP 20 20 l' homme $ the man");
        format.format(fm);
    }

    @Test(expected = MessageFormatException.class)
    public void testMissing$Delimiter() {
        Format format = new Format();
        FormatMessage fm = new FormatMessage("PP 2 2 l' homme the man & blah");
        format.format(fm);
    }

    @Test(expected = MessageFormatException.class)
    public void testMissingBeginToken() {
        Format format = new Format();
        FormatMessage fm = new FormatMessage("xx 2 2 l' homme $ the man & blah");
        format.format(fm);
    }

    @Test(expected = NumberFormatException.class)
    public void testTokenLengthNotNumber() {
        Format format = new Format();
        FormatMessage fm = new FormatMessage("PP x y l' homme $ the man & blah");
        format.format(fm);
    }
}
