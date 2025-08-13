package com.dimo.academic_affairs_system.service.impl;

import com.dimo.academic_affairs_system.mapper.RelationMapper;
import com.dimo.academic_affairs_system.pojo.Course;
import com.dimo.academic_affairs_system.pojo.Relation;
import com.dimo.academic_affairs_system.pojo.StandardException;
import com.dimo.academic_affairs_system.pojo.Student;
import com.dimo.academic_affairs_system.service.RelationService;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {
    private final RelationMapper relationMapper;

    public RelationServiceImpl(RelationMapper relationMapper) {
        this.relationMapper = relationMapper;
    }

    @Override
    public void insert_emp_course(Relation relation) {
        if(relation.getId1() == null || relation.getId2() == null){
            throw new StandardException(400, "Id cannot be null");
        }
        relationMapper.insert_emp_course(relation);
    }

    @Override
    public List<Course> get_emp_allCourses(Integer empId) {
        return relationMapper.get_emp_allCourses(empId);
    }

    @Override
    public void delete_etc(Integer empId, Integer courseId) {
        if(empId == null || courseId == null){
            throw new StandardException(400, "empId or courseId cannot be null");
        }
        relationMapper.delete_etc(empId, courseId);

    }

    @Override
    public void insert_student_course(Relation relation) {
        if(relation.getId1() == null || relation.getId2() == null){
            throw new StandardException(400, "Ids cannot be null");
        }
        relationMapper.insert_student_course(relation);
    }

    @Override
    public void delete_stc(Integer studentId, Integer courseId) {
        if(studentId == null || courseId == null){
            throw new StandardException(400, "empId or courseId cannot be null");
        }
        relationMapper.delete_stc(studentId, courseId);
    }

    @Override
    public List<Student> get_studentInCourse(Integer courseId) {
        return relationMapper.get_studentInCourse(courseId);
    }

    @Override
    public List<Course> get_student_allCourses(Integer studentId) {
        return relationMapper.get_student_allCourses(studentId);
    }
}
