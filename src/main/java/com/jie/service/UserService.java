package com.jie.service;

import com.jie.dao.UserMapper;
import com.jie.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public boolean judge(String username,String password)
    {
        User user = userMapper.findUserByUsername(username);
        if(user == null || user.getPassword().equals(password) == false)
        {
            return false;
        }
        return true;
    }

    public User findUserById(String id)
    {
        User user = userMapper.findUserById(id);
        return user;
    }

    public User findUserByName(String name)
    {
        User user = userMapper.findUserByUsername(name);
        return user;
    }

    public boolean saveUser(User user)
    {
        if(userMapper.findUserByUsername(user.getName()) != null)
        {
            return false;
        }
        user.setPower("普通用户");
        userMapper.saveUser(user);
        return false;
    }

    public boolean updateUser(User user)
    {
        if(user.getPower() == "管理员")
        {
            return false;
        }
        userMapper.updateUserByUsername(user);
        return true;
    }

    public boolean deleteUser(User user)
    {
        if(user.getPower() == "管理员")
        {
            return false;
        }
        userMapper.deleteUserByUsername(user.getName());
        return true;
    }

    public List<User> findAll()
    {
        return userMapper.getUserList();
    }
}
