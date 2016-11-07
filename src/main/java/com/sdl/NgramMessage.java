package com.sdl;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NgramMessage {

    private int maxNGramCount;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMaxNGramCount() {
        return maxNGramCount;
    }

    public void setMaxNGramCount(int maxNGramCount) {
        this.maxNGramCount = maxNGramCount;
    }

}
