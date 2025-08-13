package com.dimo.academic_affairs_system.controller;

import com.dimo.academic_affairs_system.pojo.Admin;
import com.dimo.academic_affairs_system.pojo.Result;
import com.dimo.academic_affairs_system.service.AdminService;
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
public class AdminController {
    private final AdminService adminService;
    private final StringRedisTemplate stringRedisTemplate;

    public AdminController(AdminService adminService, StringRedisTemplate stringRedisTemplate) {
        this.adminService = adminService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @PostMapping("/api/login/admin")
    public Result login(@RequestBody Admin admin){
        log.info("正在登录Admin: \"{}\"", admin.getUsername());
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        Admin a = adminService.login(admin);

        //log in success, dispatch jwt token
        if(a != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", "admin");
            claims.put("id", a.getId());
            claims.put("name", a.getName());
            claims.put("username", a.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            log.info("Admin: {} login success", a.getName());
            operations.set(jwt, a.getId().toString(), 1, TimeUnit.HOURS);
            return Result.success(jwt);
        }
        log.info("Admin: {} login fail", admin.getUsername());
        return Result.error(400, "username or password incorrect");
    }

    @GetMapping("/api/admin/{admin_id}")
    public Result get_adminInfo(@PathVariable Integer admin_id){
        log.info("now getting the admin info of: {}", admin_id);
        Admin a = adminService.get_adminInfo(admin_id);
        log.info("successfully get the info of {}", admin_id);
        return Result.success(a);
    }

    @PutMapping("/api/admin/{admin_id}")
    public Result update_adminInfo(@PathVariable Integer admin_id, @RequestBody Admin admin){
        log.info("now updating info of admin: {}", admin_id);
        adminService.update_adminInfo(admin_id, admin);
        log.info("successfully update admin: {}'s info", admin_id);
        return Result.success();
    }

    @PostMapping("/api/admin")
    public Result create_admin(@RequestBody Admin admin){
        log.info("adding new admin: {}", admin.getName());
        adminService.create_admin(admin);
        log.info("adding new admin: {} success", admin.getName());
        return Result.success();
    }

    @GetMapping("/api/admin")
    public Result get_adminList(){
        log.info("searching for all admins");
        List<Admin> a = adminService.get_adminList();
        log.info("found {} admin infos", a.size());
        return Result.success(a);
    }

    @DeleteMapping("/api/admin/{admin_id}")
    public Result delete_admin(@PathVariable Integer admin_id){
        log.info("now deleting admin: {}'s info", admin_id);
        Admin a = adminService.delete_admin(admin_id);
        log.info("successfully delete {}'s info", a);
        return Result.success(a);
    }

    @PutMapping("/api/admin/ups/{admin_id}")
    public Result updatePassword(@PathVariable Integer admin_id, @RequestBody Admin admin){
        log.info("now update password");
        adminService.update_password(admin_id, admin);
        log.info("update success");

        //delete previous token in redis
        String token = ThreadLocalUtils.get();
        log.info("token in controller: {}", token);
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);

        return Result.success();
    }
}
