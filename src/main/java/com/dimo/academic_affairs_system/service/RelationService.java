package com.dimo.academic_affairs_system.service;

import com.dimo.academic_affairs_system.pojo.Course;
import com.dimo.academic_affairs_system.pojo.Relation;
import com.dimo.academic_affairs_system.pojo.Student;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RelationService {

    /**
     * insert a relation between course and emp
     * @param relation class with empId and courseId
     */
    void insert_emp_course(Relation relation);

    /**
     * get courses the emp taught in list
     * @param empId id
     * @return the list of courses
     */
    List<Course> get_emp_allCourses(Integer empId);

    /**
     * delete etc pair
     * @param empId id
     * @param courseId id
     */
    void delete_etc(Integer empId, Integer courseId);

    /**
     * insert student and course relation
     * @param relation class with studentId and courseId
     */
    void insert_student_course(Relation relation);

    /**
     * delete student and course relation
     * @param studentId id
     * @param courseId id
     */
    void delete_stc(Integer studentId, Integer courseId);

    /**
     * search all students in a course by course_id
     * @param courseId id
     * @return a list of student info in {course_id} course
     */
    List<Student> get_studentInCourse(Integer courseId);

    /**
     * get courses the student take in list
     * @param studentId id
     * @return the list of courses
     */
    List<Course> get_student_allCourses(Integer studentId);
}
