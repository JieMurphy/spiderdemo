package com.jie.model;

import java.sql.Timestamp;

public class User {

    private Integer id;

    private String name;

    private String password;

    private int power;

    private String email;

    private Timestamp create_time;

    public User()
    {

    }

    public User(String user_name,String user_password)
    {
        this.name = user_name;
        this.password = user_password;
    }

    public User(String user_name,String user_password,String email)
    {
        this.email = email;
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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
