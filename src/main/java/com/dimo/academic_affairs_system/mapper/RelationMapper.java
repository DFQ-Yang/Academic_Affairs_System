package com.dimo.academic_affairs_system.mapper;

import com.dimo.academic_affairs_system.pojo.Course;
import com.dimo.academic_affairs_system.pojo.Relation;
import com.dimo.academic_affairs_system.pojo.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RelationMapper {
    @Insert("insert into relation.etc values(#{id1}, #{id2})")
    void insert_emp_course(Relation relation);

    @Select("select id, name, series_id, capacity, occupied from class.course where id in " +
            "(select course_id from relation.etc where emp_id = #{empId})")
    List<Course> get_emp_allCourses(Integer empId);

    @Delete("delete from relation.etc where emp_id = #{empId} and course_id = #{courseId}")
    void delete_etc(Integer empId, Integer courseId);


    @Insert("insert into relation.etc values(#{id1}, #{id2})")
    void insert_student_course(Relation relation);

    @Delete("delete from relation.stc where student_id = #{studentId} and course_id = #{courseId}")
    void delete_stc(Integer studentId, Integer courseId);

    @Select("select id, username, name, gpa, level, unit from user.student where id in " +
            "(select student_id from relation.stc where course_id = #{courseId})")
    List<Student> get_studentInCourse(Integer courseId);

    @Select("select id, name, series_id, capacity, occupied from class.course where id in " +
            "(select course_id from relation.stc where student_id = #{studentId})")
    List<Course> get_student_allCourses(Integer studentId);
}
