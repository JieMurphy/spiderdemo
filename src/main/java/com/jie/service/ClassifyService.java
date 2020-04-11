package com.jie.service;

import com.jie.dao.ClassifyMapper;
import com.jie.model.Classify;
import com.jie.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassifyService {
    @Autowired
    private ClassifyMapper classifyMapper;

    @Autowired
    private UserService userService;

    public List<Classify> findByParentId(int parent_id)
    {
        return classifyMapper.findClassifyByParentId(parent_id);
    }

    public Classify findById(int id)
    {
        return classifyMapper.findClassifyById(id);
    }

    public List<Classify> findAll()
    {
        return classifyMapper.findAll();
    }

    public Boolean saveClassify(Classify classify,User user)
    {
        if(user.getPower() != userService.管理员)
        {
            return false;
        }
        Classify classify1 = classifyMapper.findClassifyById(classify.getParent_id());
        if(classify1 == null || classify.getType() == null)
        {
            return false;
        }
        classify.setLevel(classify1.getLevel() + 1);
        classifyMapper.saveClassify(classify);
        return true;
    }

    public Boolean updateType(Classify classify,User user)
    {
        if(user.getPower() != userService.管理员)
        {
            return false;
        }
        classifyMapper.updateType(classify);
        return true;
    }

    public Boolean deleteClassify(int id,User user)
    {
        if(user.getPower() != userService.管理员)
        {
            return false;
        }
        classifyMapper.deleteClassifyById(id);
        return true;
    }
}
