package com.dimo.academic_affairs_system.service;

import com.dimo.academic_affairs_system.pojo.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    /**
     * create a new course
     * @param course A course class with course info
     */
    void create_course(Course course);

    /**
     * search for all courses in the table
     * @return a list of courses and infos
     */
    List<Course> get_courseList();

    /**
     * find specific course by id
     * @param courseId used to find specific course
     * @return course info
     */
    Course get_courseInfo(Integer courseId);

    /**
     * update course info by id
     * @param courseId the id used to direct course
     * @param course the context to modify
     */
    void update_courseInfo(Integer courseId, Course course);

    /**
     * delete course
     * delete etc, stc related to this course
     * @param courseId used to find the course
     * @return deleted course info
     */
    Course delete_course(Integer courseId);
}
