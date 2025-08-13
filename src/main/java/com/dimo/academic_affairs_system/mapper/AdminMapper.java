package com.dimo.academic_affairs_system.mapper;

import com.dimo.academic_affairs_system.pojo.Admin;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface AdminMapper {
    @Select("select id, username, name from user.admin where username = #{username} and password = #{password}")
    Admin login(Admin admin);

    @Select("select id, username, name from user.admin where id = #{adminId}")
    Admin get_adminInfo(Integer adminId);

    @Update("update user.admin set password = #{admin.password} where id = #{adminId}")
    void update_adminInfo(Integer adminId, Admin admin);

    @Insert("insert into user.admin values(#{id}, #{username}, #{password}, #{name})")
    void create_admin(Admin admin);

    @Select("select id, username, password, name from user.admin")
    List<Admin> get_allAdmin();

    @Delete("delete from user.admin where id = #{adminId}")
    void delete_admin(Integer adminId);

    @Update("update user.admin set password = #{admin.password} where id = #{adminId}")
    void update_password(Integer adminId, Admin admin);
}
