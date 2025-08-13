package com.dimo.academic_affairs_system.mapper;

import com.dimo.academic_affairs_system.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("select id, username, name from user.student where username = #{username} and password = #{password}")
    Student login(Student student);

    @Insert("insert into user.student values(null, #{username}, #{password}, #{name}, #{gpa}, #{level}, #{unit})")
    void create_student(Student student);

    @Select("select id, username, name, gpa, level, unit from user.student")
    List<Student> get_studentList();

    @Select("select id, username, name, gpa, level, unit from user.student where id = #{studentId}")
    Student get_studentInfo(Integer studentId);

    @Update("update user.student set password = #{student.password}, gpa = #{student.gpa} where id = #{studentId}")
    void update_studentInfo(Integer studentId, Student student);

    void delete_student(Integer studentId);

    @Update("update user.student set password = #{student.password} where id = #{studentId}")
    void update_password(Integer studentId, Student student);
}
