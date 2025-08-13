package com.dimo.academic_affairs_system.controller;

import com.dimo.academic_affairs_system.pojo.Admin;
import com.dimo.academic_affairs_system.pojo.Emp;
import com.dimo.academic_affairs_system.pojo.Result;
import com.dimo.academic_affairs_system.service.EmpService;
import com.dimo.academic_affairs_system.utils.JwtUtils;
import com.dimo.academic_affairs_system.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class EmpController {
    private final EmpService empService;
    private final StringRedisTemplate stringRedisTemplate;

    public EmpController(EmpService empService, StringRedisTemplate stringRedisTemplate) {
        this.empService = empService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @PostMapping("/api/login/emp")
    public Result login(@RequestBody Emp emp){
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        log.info("Emp: {} trying to log in", emp.getUsername());
        Emp e = empService.login(emp);

        if(e != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("role", "emp");
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            log.info("Emp: {} login success", e.getName());
            operations.set(jwt, e.getId().toString(), 1, TimeUnit.HOURS);
            return Result.success(jwt);
        }
        log.info("Emp: {} login fail", emp.getUsername());
        return Result.error(400, "username or password incorrect");
    }

    @GetMapping("/api/emp/{emp_id}")
    public Result get_empInfo(@PathVariable Integer emp_id){
        log.info("now getting the emp info of: {}", emp_id);
        Emp e = empService.get_empInfo(emp_id);
        log.info("successfully get the info of {}", emp_id);
        return Result.success(e);
    }

    @PutMapping("/api/emp/{emp_id}")
    public Result update_empInfo(@PathVariable Integer emp_id, @RequestBody Emp emp){
        log.info("now updating info of emp: {}", emp_id);
        empService.update_empInfo(emp_id, emp);
        log.info("successfully update emp: {}'s info", emp_id);
        return Result.success();
    }

    @PostMapping("/api/emp")
    public Result create_emp(@RequestBody Emp emp){
        log.info("adding new emp: {}", emp.getName());
        empService.create_emp(emp);
        log.info("adding new emp: {} success", emp.getName());
        return Result.success();
    }

    @GetMapping("/api/emp")
    public Result get_empList(){
        log.info("searching for all emps");
        List<Emp> e = empService.get_empList();
        log.info("found {} emp infos", e.size());
        return Result.success(e);
    }

    @DeleteMapping("/api/emp/{emp_id}")
    public Result delete_emp(@PathVariable Integer emp_id){
        log.info("now deleting emp: {}'s info", emp_id);
        Emp e = empService.delete_emp(emp_id);
        log.info("successfully delete {}'s info", e);
        return Result.success(e);
    }

    @PutMapping("/api/emp/ups/{emp_id}")
    public Result updatePassword(@PathVariable Integer emp_id, @RequestBody Emp emp){
        log.info("now update password");
        empService.update_password(emp_id, emp);
        log.info("update success");

        //delete previous token in redis
        String token = ThreadLocalUtils.get();
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);

        return Result.success();
    }
}
