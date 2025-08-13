package com.dimo.academic_affairs_system.controller;

import com.dimo.academic_affairs_system.pojo.Course;
import com.dimo.academic_affairs_system.pojo.Relation;
import com.dimo.academic_affairs_system.pojo.Result;
import com.dimo.academic_affairs_system.pojo.Student;
import com.dimo.academic_affairs_system.service.RelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class RelationController {
    private final RelationService relationService;

    public RelationController(RelationService relationService) {
        this.relationService = relationService;
    }

    @PostMapping("/api/etc")
    public Result insert_emp_course(@RequestBody Relation relation){
        log.info("now adding a new relation between emp and course");
        relationService.insert_emp_course(relation);
        log.info("adding success");
        return Result.success();
    }

    @GetMapping("/api/etc/{emp_id}")
    public Result get_emp_allCourses(@PathVariable Integer emp_id){
        log.info("now searching for all courses emp: {} taught", emp_id);
        List<Course> courses = relationService.get_emp_allCourses(emp_id);
        log.info("searching success");
        return Result.success(courses);
    }

    @DeleteMapping("/api/etc")
    public Result delete_etc(@RequestParam Integer emp_id, @RequestParam Integer course_id){
        log.info("now deleting etc info: emp: {}, course: {}", emp_id, course_id);
        relationService.delete_etc(emp_id, course_id);
        log.info("delete success");
        return Result.success();
    }

    @PostMapping("/api/stc")
    public Result add_stc(@RequestBody Relation relation){
        log.info("now adding a new relation between student and course");
        relationService.insert_student_course(relation);
        log.info("adding success");
        return Result.success();
    }

    @GetMapping("/api/etc/{student_id}")
    public Result get_student_allCourses(@PathVariable Integer student_id){
        log.info("now searching for all courses student: {} taught", student_id);
        List<Course> courses = relationService.get_student_allCourses(student_id);
        log.info("searching success");
        return Result.success(courses);
    }
    @DeleteMapping("/api/stc")
    public Result delete_stc(@RequestParam Integer student_id, @RequestParam Integer course_id){
        log.info("now deleting etc info: student: {}, course: {}", student_id, course_id);
        relationService.delete_stc(student_id, course_id);
        log.info("delete success");
        return Result.success();
    }

    @GetMapping("/api/stc/{course_id}")
    public Result get_studentsInCourse(@PathVariable Integer course_id){
        log.info("now searching for all students in course: {} taught", course_id);
        List<Student> students = relationService.get_studentInCourse(course_id);
        log.info("searching success");
        return Result.success(students);
    }
}
