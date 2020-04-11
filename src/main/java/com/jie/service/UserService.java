package com.jie.service;

import com.jie.dao.UserMapper;
import com.jie.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    public int 普通用户 = -1;
    public int 超级用户 = 0;
    public int 管理员 = 1;

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

    public User findUserById(String id,User user)
    {
        if(user.getPower() != 管理员)
        {
            return null;
        }
        return userMapper.findUserById(id);
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
        user.setPower(普通用户);
        userMapper.saveUser(user);
        return true;
    }

    public boolean updateUser(User user)
    {
        if(user.getPower() == 管理员)
        {
            return false;
        }
        userMapper.updateUserPassword(user);
        return true;
    }

    public boolean updateUser(User user1,User user)
    {
        if(user.getPower() != 管理员 || user.getPower() == 管理员)
        {
            return false;
        }
        userMapper.updateUserPower(user1);
        return true;
    }

    public boolean deleteUser(int id,User user)
    {
        if(user.getPower() != 管理员 || id == user.getId())
        {
            return false;
        }
        userMapper.deleteUserById(id);
        return true;
    }

    public List<User> findAll(User user)
    {
        if(user.getPower() != 管理员)
        {
            return null;
        }
        return userMapper.getUserList();
    }
}
