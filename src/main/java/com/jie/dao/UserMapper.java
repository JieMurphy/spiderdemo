package com.jie.dao;

import com.jie.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("select * from user where name = #{username}")
    User findUserByUsername(String username);

    @Select("select * from user where id = #{id}")
    User findUserById(String id);

    @Update("update user set password = #{password} where name = #{name}")
    void updateUserByUsername(User user);

    @Delete("delete from user where name = #{username}")
    void deleteUserByUsername(String username);

    @Insert("insert into user(name,password,rank) values(#{name},#{password})")
    void saveUser(User user);

    @Select("select * from user")
    List<User> getUserList();
}
