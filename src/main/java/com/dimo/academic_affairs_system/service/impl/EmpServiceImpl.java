package com.dimo.academic_affairs_system.service.impl;

import com.dimo.academic_affairs_system.mapper.EmpMapper;
import com.dimo.academic_affairs_system.pojo.Admin;
import com.dimo.academic_affairs_system.pojo.Emp;
import com.dimo.academic_affairs_system.pojo.StandardException;
import com.dimo.academic_affairs_system.service.EmpService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    private final EmpMapper empMapper;

    public EmpServiceImpl(EmpMapper empMapper) {
        this.empMapper = empMapper;
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.login(emp);
    }

    @Override
    public Emp get_empInfo(Integer empId) {
        return empMapper.get_empInfo(empId);
    }

    @Override
    public void update_empInfo(Integer empId, Emp emp) {
        if(emp.getName() == null){
            throw new StandardException(400, "name cannot be null");
        }
        empMapper.update_empInfo(empId, emp);
    }

    @Override
    public void create_emp(Emp emp) {
        if(emp.getName() == null || emp.getUsername() == null){
            throw new StandardException(400, "username or name cannot be null");
        }
        if(emp.getPassword() == null){
            emp.setPassword("123456");
        }
        emp.setEntrydate(LocalDate.now());
        try{
            empMapper.create_emp(emp);
        }
        catch (DuplicateKeyException e){
            throw new StandardException(400, "username already exist");
        }
    }

    @Override
    public List<Emp> get_empList() {
        return empMapper.get_allEmp();
    }

    @Transactional
    @Override
    public Emp delete_emp(Integer empId) {
        Emp e = empMapper.get_empInfo(empId);
        empMapper.delete_emp(empId);
        return e;
    }

    @Override
    public void update_password(Integer empId, Emp emp) {
        if(emp.getPassword() == null){
            throw new StandardException(400, "password cannot be null");
        }
        empMapper.update_password(empId, emp);
    }
}
