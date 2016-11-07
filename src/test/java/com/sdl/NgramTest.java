package com.sdl;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class NgramTest {

    @Test
    public void testNgramBreakdown() {
        NgramMessage message = new NgramMessage();
        message.setMaxNGramCount(3);
        message.setText("do you know that I know you know that I know that ?");

        Ngram ngram = new Ngram();
        List<NgramResponse> response = ngram.getNgrams(message);

        NgramResponse ngram1 = new NgramResponse("you know that", 2);
        NgramResponse ngram2 = new NgramResponse("know that I", 2);
        NgramResponse ngram3 = new NgramResponse("I know you", 1);
        NgramResponse ngram4 = new NgramResponse("know that", 3);
        NgramResponse ngram5 = new NgramResponse("I know", 2);
        NgramResponse ngram6 = new NgramResponse("know", 4);
        NgramResponse ngram7 = new NgramResponse("xxx", 2);

        Assert.assertTrue(response.contains(ngram1));
        Assert.assertTrue(response.contains(ngram2));
        Assert.assertTrue(response.contains(ngram3));
        Assert.assertTrue(response.contains(ngram4));
        Assert.assertTrue(response.contains(ngram5));
        Assert.assertTrue(response.contains(ngram6));
        Assert.assertFalse(response.contains(ngram7));
    }
}
