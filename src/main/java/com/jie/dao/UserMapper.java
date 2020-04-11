package com.jie.dao;

import com.jie.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("select * from user where name = #{username}")
    User findUserByUsername(String username);

    @Select("select * from user where id = #{id}")
    User findUserById(String id);

    @Select("select * from user")
    List<User> getUserList();

    @Update("update user set password = #{password} where name = #{name}")
    void updateUserPassword(User user);

    @Update("update user set power = #{power} where id = #{id}")
    void updateUserPower(User user);

    @Delete("delete from user where id = #{id}")
    void deleteUserById(int id);

    @Insert("insert into user(name,password,power,email) values(#{name},#{password},#{power},#{email})")
    void saveUser(User user);


}
