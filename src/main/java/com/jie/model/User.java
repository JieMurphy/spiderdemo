package com.jie.model;

import java.sql.Timestamp;

public class User {

    private Integer id;

    private String name;

    private String password;

    private String power;

    private Timestamp create_time;

    public User()
    {

    }

    public User(String user_name,String user_password)
    {
        this.name = user_name;
        this.password = user_password;
    }

    public User(int id,String user_name,String user_password)
    {
        this.id = id;
        this.name = user_name;
        this.password = user_password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String user_name) {
        this.name = user_name;
    }

    public void setPassword(String user_password) {
        this.password = user_password;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }
}
