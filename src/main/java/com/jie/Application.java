package com.jie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@MapperScan("com.jie.dao")
public class Application{

    public static void main(String[] args)
    {
        //Spider.create(new ListPageProcesser()).addUrl("https://www.dytt8.net/html/gndy/dyzz/list_23_1.html").addPipeline(new MyPipeline()).thread(1).run();
        //Spider.create(new DetailPageProcesser()).addUrl("https://www.dytt8.net/html/gndy/dyzz/list_23_1.html").addPipeline(new MyPipeline()).thread(1).run();
        SpringApplication.run(Application.class,args);
    }

}
