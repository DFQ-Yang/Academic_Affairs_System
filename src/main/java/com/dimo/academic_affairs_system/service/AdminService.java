package com.dimo.academic_affairs_system.service;

import com.dimo.academic_affairs_system.pojo.Admin;
import org.springframework.stereotype.Service;


import java.util.List;
@Service

public interface AdminService {

    /**
     * admin login
     * @param admin  login info contain username & password
     * @return return the info matches with the username and password
     */
    Admin login(Admin admin);

    /**
     * search for admin's info by id
     * @param adminId the id that needs to be search
     * @return admin info
     */
    Admin get_adminInfo(Integer adminId);

    /**
     * update admin's info
     * @param adminId the admin's id that needs to be modified
     * @param admin the context that we want to change
     */
    void update_adminInfo(Integer adminId, Admin admin);

    /**
     * Create a new admin with default password"123456", entrydate"now()
     * @param admin contains "username" and "name"
     */
    void create_admin(Admin admin);

    /**
     * List all adminloyees in Admin table
     * @return A list with all admin infos
     */
    List<Admin> get_adminList();

    /**
     * delete admin which id = adminId
     * @param adminId the id that needs to be deleted
     * @return deleted admin's info without password
     */
    Admin delete_admin(Integer adminId);

    /**
     * update password
     *
     * @param adminId id
     * @param admin
     */
    void update_password(Integer adminId, Admin admin);
}
