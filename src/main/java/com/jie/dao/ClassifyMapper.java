package com.jie.dao;

import com.jie.model.Classify;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ClassifyMapper {
    @Select("select * from classify")
    List<Classify> findAll();

    @Select("select * from classify where id = #{id}")
    Classify findClassifyById(int id);

    @Select("select * from classify where parent_id = #{parent_id}")
    List<Classify> findClassifyByParentId(int parent_id);

    @Insert("insert into classify(type,parent_id,level) values(#{type},#{parent_id},#{level})")
    void saveClassify(Classify classify);

    @Delete("delete from classify where id = #{id}")
    void deleteClassifyById(int id);

    @Update("update classify set type = #{type} where id = #{id}")
    void updateType(Classify classify);
}
