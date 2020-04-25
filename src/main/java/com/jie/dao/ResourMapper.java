package com.jie.dao;

import com.jie.model.Resour;
import com.jie.model.SortModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ResourMapper {
    @Select("select * from resources where id = #{id}")
    Resour findResourById(int id);

    @Select("select * from resources where path = #{path}")
    Resour findResourByPath(String path);

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

    @Update("update resources set path = #{path} , body = #{body} , ftype = #{ftype} where id = #{id}")
    void updatePathBodyFtype(Resour resour);

    @Update("update resources set title = #{title} , body = #{body} , user_id = #{user_id} where id = #{id}")
    void updateTitleUserEtc(Resour resour);

    @Update("update resources set first = #{first} , second = #{second} , third = #{third} , forth = #{forth} where id = #{id}")
    void updateClassifies(Resour resour);

    @Insert("insert into resources (title,status) VALUES (#{title},#{status})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer saveResour(Resour resour);

    @Delete("delete from resources where id = #{id}")
    void deleteResour(int id);
}
