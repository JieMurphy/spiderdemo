package com.jie.model;

public class SearchModel {
    private String keywords;

    public SearchModel()
    {

    }

    public void setKeywords(String ketywords) {
        this.keywords = ketywords;
    }

    public String getKeywords() {
        return keywords;
    }

    public String toString()
    {
        return this.keywords;
    }
}
