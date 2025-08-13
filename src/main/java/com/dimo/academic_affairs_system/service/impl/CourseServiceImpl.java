package com.dimo.academic_affairs_system.service.impl;

import com.dimo.academic_affairs_system.mapper.CourseMapper;
import com.dimo.academic_affairs_system.pojo.Course;
import com.dimo.academic_affairs_system.pojo.StandardException;
import com.dimo.academic_affairs_system.service.CourseService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public void create_course(Course course) {
        if(course.getName() == null || course.getSeries_id() == null || course.getCapacity() == null){
            throw new StandardException(400, "name, series_id or capacity not given");
        }
        if(course.getOccupied() == null){
            course.setOccupied((short)0);
        }
        try{
            courseMapper.create_course(course);
        }
        catch (DuplicateKeyException e){
            throw new StandardException(400, "course name is already exist");
        }
    }

    @Override
    public List<Course> get_courseList() {
        return courseMapper.get_courseList();
    }

    @Override
    public Course get_courseInfo(Integer courseId) {
        return courseMapper.get_courseInfo(courseId);
    }

    @Override
    public void update_courseInfo(Integer courseId, Course course) {
        courseMapper.update_courseInfo(courseId, course);
    }

    @Transactional
    @Override
    public Course delete_course(Integer courseId) {
        Course c = courseMapper.get_courseInfo(courseId);
        courseMapper.delete_course(courseId);
        return c;
    }
}
