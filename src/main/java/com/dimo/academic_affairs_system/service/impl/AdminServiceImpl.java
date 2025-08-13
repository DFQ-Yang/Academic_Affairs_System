package com.dimo.academic_affairs_system.service.impl;

import com.dimo.academic_affairs_system.mapper.AdminMapper;
import com.dimo.academic_affairs_system.pojo.Admin;
import com.dimo.academic_affairs_system.pojo.StandardException;
import com.dimo.academic_affairs_system.service.AdminService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;


    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin login(Admin admin) {
        return adminMapper.login(admin);
    }

    @Override
    public Admin get_adminInfo(Integer adminId) {
        return adminMapper.get_adminInfo(adminId);
    }

    @Override
    public void update_adminInfo(Integer adminId, Admin admin) {
        if(admin.getPassword() == null){
            throw new StandardException(400, "password cannot be null");
        }
        adminMapper.update_adminInfo(adminId, admin);
    }

    @Override
    public void create_admin(Admin admin) {
        if(admin.getName() == null || admin.getUsername() == null){
            throw new StandardException(400, "username or name cannot be null");
        }
        if(admin.getPassword() == null){
            admin.setPassword("123456");
        }
        try{
            adminMapper.create_admin(admin);
        }
        catch (DuplicateKeyException a){
            throw new StandardException(400, "username already exist");
        }
    }

    @Override
    public List<Admin> get_adminList() {
        return adminMapper.get_allAdmin();
    }

    @Transactional
    @Override
    public Admin delete_admin(Integer adminId) {
        Admin a = adminMapper.get_adminInfo(adminId);
        adminMapper.delete_admin(adminId);
        return a;
    }

    @Override
    public void update_password(Integer adminId, Admin admin) {
        if(admin.getPassword() == null){
            throw new StandardException(400, "password cannot be null");
        }
        adminMapper.update_password(adminId, admin);
    }
}
