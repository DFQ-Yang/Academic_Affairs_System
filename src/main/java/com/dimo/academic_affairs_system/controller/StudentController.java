package com.dimo.academic_affairs_system.controller;

import com.dimo.academic_affairs_system.pojo.Emp;
import com.dimo.academic_affairs_system.pojo.Result;
import com.dimo.academic_affairs_system.pojo.Student;
import com.dimo.academic_affairs_system.service.StudentService;
import com.dimo.academic_affairs_system.utils.JwtUtils;
import com.dimo.academic_affairs_system.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class StudentController {
    private final StudentService studentService;
    private final StringRedisTemplate stringRedisTemplate;

    public StudentController(StringRedisTemplate stringRedisTemplate, StudentService studentService) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.studentService = studentService;
    }

    @PostMapping("/api/login/student")
    public Result login(@RequestBody Student student){
        log.info("Student: {} trying to log in", student.getUsername());
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        Student s = studentService.login(student);

        if(s != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", s.getId());
            claims.put("role", "student");
            claims.put("name", s.getName());
            claims.put("username", s.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            log.info("Stduent: {} login success", s.getName());
            operations.set(jwt, s.getId().toString(), 1, TimeUnit.HOURS);
            return Result.success(jwt);
        }
        log.info("Student: {} login fail", student.getUsername());
        return Result.error(400, "username or password incorrect");
    }

    @PostMapping("/api/student")
    public Result create_student(@RequestBody Student student){
        log.info("now creating a new student");
        studentService.create_student(student);
        log.info("creating success");
        return Result.success();
    }

    @GetMapping("/api/student")
    public Result get_studentList(){
        log.info("searching for all students");
        List<Student> s = studentService.get_studentList();
        log.info("found {} student infos", s.size());
        return Result.success(s);
    }

    @GetMapping("/api/student/{student_id}")
    public Result get_studentInfo(@PathVariable Integer student_id){
        log.info("now getting the student info of {}", student_id);
        Student s = studentService.get_studentInfo(student_id);
        log.info("get student info success");
        return Result.success(s);
    }

    @PutMapping("/api/student/{student_id}")
    public Result update_studentInfo(@PathVariable Integer student_id, @RequestBody Student student){
        log.info("now updating the info of student: {}", student_id);
        studentService.update_studentInfo(student_id, student);
        log.info("updating student info success");
        return Result.success();
    }

    @DeleteMapping("/api/student/{student_id}")
    public Result delete_student(@PathVariable Integer student_id){
        log.info("deleting student: {}", student_id);
        Student s = studentService.delete_student(student_id);
        log.info("student: {} info delete success", student_id);
        return Result.success(s);
    }

    @PutMapping("/api/student/ups/{student_id}")
    public Result updatePassword(@PathVariable Integer student_id, @RequestBody Student student){
        log.info("now update password");
        studentService.update_password(student_id, student);
        log.info("update success");

        //delete previous token in redis
        String token = ThreadLocalUtils.get();
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);

        return Result.success();
    }
}
