package com.dimo.academic_affairs_system.service.impl;

import com.dimo.academic_affairs_system.mapper.StudentMapper;
import com.dimo.academic_affairs_system.pojo.StandardException;
import com.dimo.academic_affairs_system.pojo.Student;
import com.dimo.academic_affairs_system.service.StudentService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public Student login(Student student) {
        return studentMapper.login(student);
    }

    @Override
    public void create_student(Student student) {
        if(student.getPassword() == null || student.getUsername() == null || student.getName() == null || student.getLevel() == null){
            throw new StandardException(400, "username, name, password or level  not declare");
        }
        if(student.getGpa() == null){
            student.setGpa((float)4);
        }
        if(student.getUnit() == null){
            student.setUnit((short)0);
        }
        try{
            studentMapper.create_student(student);
        }
        catch (DuplicateKeyException e){
            throw new StandardException(400, "username is already exist");
        }
    }

    @Override
    public List<Student> get_studentList() {
        return studentMapper.get_studentList();
    }

    @Override
    public Student get_studentInfo(Integer studentId) {
        return studentMapper.get_studentInfo(studentId);
    }

    @Override
    public void update_studentInfo(Integer studentId, Student student) {
        if(student.getPassword() == null || student.getGpa() == null){
            throw new StandardException(400, "password or gpa cannot be null");
        }
        studentMapper.update_studentInfo(studentId, student);
    }

    @Transactional
    @Override
    public Student delete_student(Integer studentId) {
        Student s = studentMapper.get_studentInfo(studentId);
        studentMapper.delete_student(studentId);
        return s;
    }

    @Override
    public void update_password(Integer studentId, Student student) {
        if(student.getPassword() == null){
            throw new StandardException(400, "password cannot be null");
        }
        studentMapper.update_password(studentId, student);
    }
}
