package com.dimo.academic_affairs_system.controller;

import com.dimo.academic_affairs_system.pojo.Course;
import com.dimo.academic_affairs_system.pojo.Result;
import com.dimo.academic_affairs_system.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/api/course")
    public Result create_course(@RequestBody Course course){
        log.info("now creating a new course");
        courseService.create_course(course);
        log.info("course: {} creating success", course.getName());
        return Result.success();

    }

    @GetMapping("/api/course")
    public Result get_courseList(){
        log.info("now searching all courses");
        List<Course> c = courseService.get_courseList();
        log.info("Found {} course", c.size());
        return Result.success(c);
    }

    @GetMapping("/api/course/{course_id}")
    public Result get_courseInfo(@PathVariable Integer course_id){
        log.info("now finding the course id: {}", course_id);
        Course course = courseService.get_courseInfo(course_id);
        log.info("found the course");
        return Result.success(course);
    }

    @PutMapping("/api/course/{course_id}")
    public Result update_courseInfo(@PathVariable Integer course_id, @RequestBody Course course){
        log.info("now updating the info of student: {}", course_id);
        courseService.update_courseInfo(course_id, course);
        log.info("updating course info success");
        return Result.success();
    }

    @DeleteMapping("/api/course/{course_id}")
    public Result delete_student(@PathVariable Integer course_id){
        log.info("deleting course: {}", course_id);
        Course c = courseService.delete_course(course_id);
        log.info("course: {} info delete success", course_id);
        return Result.success(c);
    }
}
