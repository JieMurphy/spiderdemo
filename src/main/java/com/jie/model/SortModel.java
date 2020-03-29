package com.jie.model;

public class SortModel {
    private Integer first;
    private Integer second;
    private Integer third;
    private Integer forth;

    public SortModel()
    {

    }

    public void setThird(Integer third) {
        this.third = third;
    }

    public Integer getThird() {
        return third;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public Integer getSecond() {
        return second;
    }

    public void setForth(Integer forth) {
        this.forth = forth;
    }

    public Integer getForth() {
        return forth;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getFirst() {
        return first;
    }

    public String toString()
    {
        return this.first + " " + this.second + " " + this.third + " " + this.forth;
    }
}
