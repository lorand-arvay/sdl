package com.sdl;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class NgramResponse implements Comparable {

    private String ngram;
    private int count;

    public NgramResponse() {
    }

    public NgramResponse(String ngram, int count) {
        this.ngram = ngram;
        this.count = count;
    }

    public String getNgram() {
        return ngram;
    }

    public void setNgram(String text) {
        this.ngram = text;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(Object o) {
        NgramResponse other = (NgramResponse)o;
        return other.getCount() - this.getCount();
    }

    @Override
    public boolean equals(Object o) {
        NgramResponse other = (NgramResponse)o;
        return this.ngram.equals(other.getNgram()) && this.count == other.getCount();
    }
}
