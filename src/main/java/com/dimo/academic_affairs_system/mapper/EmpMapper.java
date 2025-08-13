package com.dimo.academic_affairs_system.mapper;

import com.dimo.academic_affairs_system.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmpMapper {
    @Select("select id, username, name from user.emp where username = #{username} and password = #{password}")
    Emp login(Emp emp);

    @Select("select id, username, name, entrydate from user.emp where id = #{empId}")
    Emp get_empInfo(Integer empId);

    @Update("update user.emp set name = #{emp.name}, password = #{emp.password} where id = #{empId}")
    void update_empInfo(Integer empId, Emp emp);

    @Insert("insert into user.emp values(#{id}, #{username}, #{password}, #{name}, #{entrydate})")
    void create_emp(Emp emp);

    @Select("select id, username, password, name, entrydate from user.emp")
    List<Emp> get_allEmp();

    void delete_emp(Integer empId);

    @Update("update user.emp set password = #{emp.password} where id = #{empId}")
    void update_password(Integer empId, Emp emp);
}
