package com.dimo.academic_affairs_system.service;

import com.dimo.academic_affairs_system.pojo.Emp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmpService {
    Emp login(Emp emp);

    /**
     * search for emp's info by id
     * @param empId the id that needs to be search
     * @return emp info
     */
    Emp get_empInfo(Integer empId);

    /**
     * update emp's info
     * @param empId the emp's id that needs to be modified
     * @param emp the context that we want to change
     */
    void update_empInfo(Integer empId, Emp emp);

    /**
     * Create a new emp with default password"123456", entrydate"now()
     * @param emp contains "username" and "name"
     */
    void create_emp(Emp emp);

    /**
     * List all employees in Emp table
     * @return A list with all emp infos
     */
    List<Emp> get_empList();

    /**
     * delete emp which id = empId
     * @param empId the id that needs to be deleted
     * @return deleted emp's info without password
     */
    Emp delete_emp(Integer empId);

    /**
     * update password
     * @param empId id
     * @param emp class with password
     */
    void update_password(Integer empId, Emp emp);
}
