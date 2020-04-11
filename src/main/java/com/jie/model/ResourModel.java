package com.jie.model;

public class ResourModel {
    private Integer id;
    private String title;
    private String body;
    private String path;
    private String ftype;
    private String user_name;

    public ResourModel()
    {

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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getFtype() {
        return ftype;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }
}
