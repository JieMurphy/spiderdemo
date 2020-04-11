package com.jie.dao;

import com.jie.model.Resour;
import com.jie.model.SortModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ResourMapper {
    @Select("select * from resources where id = #{id}")
    Resour findResourById(int id);

    @Select("select * from resources")
    List<Resour> findAll();

    @Select("select * from resources where user_id = #{id}")
    List<Resour> findResourByUserid(int id);

    @Select("select * from resources where status=1 and match(title,body) against(#{data} in natural language mode)")
    List<Resour> findResourByMatch(String data);

    @Select({"<script>",
            "select * from resources",
            "where status=1",
            "<when test='first!=null'>",
            "and first = #{first}",
            "</when>",
            "<when test='second!=null'>",
            "and second = #{second}",
            "</when>",
            "<when test='third!=null'>",
            "and third = #{third}",
            "</when>",
            "<when test='forth!=null'>",
            "and forth = #{forth}",
            "</when>",
            "</script>"
            })
    List<Resour> findByFilter(SortModel sortModel);

    @Select("select * from resources where status = #{status}")
    List<Resour> findByStatus(int status);

    @Update("update resources set status = #{status} where id = #{id}")
    void updateStatusById(Resour resour);

    @Insert("insert into resources (title, body, user_id, path, first, second, third, forth) VALUES (#{title},#{body},#{user_id},#{path},#{first},#{second},#{third},#{forth})")
    void saveResour(Resour resour);

    @Delete("delete from resources where id = #{id}")
    void deleteResour(int id);
}
