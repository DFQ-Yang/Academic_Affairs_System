package com.dimo.academic_affairs_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Float gpa;
    private Short level;
    private Short unit;
}
