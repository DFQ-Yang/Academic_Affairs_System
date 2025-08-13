package com.dimo.academic_affairs_system.mapper;


import com.dimo.academic_affairs_system.pojo.Series;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SeriesMapper {
    @Insert("insert into class.series values(null, #{name})")
    void create_series(Series series);

    @Select("select id, name from class.series")
    List<Series> get_allSeries();

    @Update("update class.series set name = #{series.name} where id = #{seriesId}")
    void update_series(Integer seriesId, Series series);


    void delete_series(Integer seriesId);
}
