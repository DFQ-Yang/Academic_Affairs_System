package com.dimo.academic_affairs_system.service;

import com.dimo.academic_affairs_system.pojo.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    Student login(Student student);

    /**
     * create a new student
     * @param student A Student class object contains student's info
     */
    void create_student(Student student);

    /**
     * search for all student in the table
     * @return a list of student
     */
    List<Student> get_studentList();

    /**
     * search for personal info
     * @param studentId used to find the student's info
     * @return Student class with student's info
     */
    Student get_studentInfo(Integer studentId);

    /**
     * updating student's info by id
     * @param studentId id that used to find the student
     */
    void update_studentInfo(Integer studentId, Student student);

    /**
     * delete student by id
     * @param studentId used to find the student we are looking for
     * @return the deleted student's info
     */
    Student delete_student(Integer studentId);

    /**
     * update password
     * @param studentId id
     * @param student class with password
     */
    void update_password(Integer studentId, Student student);
}
