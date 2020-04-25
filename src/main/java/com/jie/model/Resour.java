package com.jie.model;

import java.sql.Timestamp;

public class Resour {
    private Integer id;
    private String title;
    private String body;
    private Integer user_id;
    private String path;
    private Integer first;
    private Integer second;
    private Integer third;
    private Integer forth;
    private int status;
    private String ftype;
    private Timestamp create_time;
    private Timestamp update_time;

    public Resour()
    {

    }

    public Resour(String title,int status)
    {
        this.title = title;
        this.status = status;
        this.body = "";
        this.ftype = "";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getFirst() {
        return first;
    }

    public void setForth(Integer forth) {
        this.forth = forth;
    }

    public Integer getForth() {
        return forth;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public Integer getSecond() {
        return second;
    }

    public void setThird(Integer third) {
        this.third = third;
    }

    public Integer getThird() {
        return third;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public int getStatus() {
        return status;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }
}
