package com.jie.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

public class MyPipeline implements Pipeline {
    private final static Logger logger = LoggerFactory.getLogger(MyPipeline.class);
    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("get page:" + resultItems.getRequest().getUrl());
        for(Map.Entry<String,Object> entry : resultItems.getAll().entrySet())
        {
            logger.info(entry.getKey() + ":\t" + entry.getValue());
        }
    }
}
