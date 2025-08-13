package com.dimo.academic_affairs_system.mapper;

import com.dimo.academic_affairs_system.pojo.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Insert("insert into class.course values(null, #{name}, #{series_id}, #{capacity}, #{occupied})")
    void create_course(Course course);

    @Select("select id, name, series_id, capacity, occupied from class.course")
    List<Course> get_courseList();

    @Select("select * from class.course where id = #{courseId}")
    Course get_courseInfo(Integer courseId);

    @Update("update class.course set capacity = #{course.capacity} where id = #{courseId}")
    void update_courseInfo(Integer courseId, Course course);

    void delete_course(Integer courseId);
}
