package com.dimo.academic_affairs_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Integer id;
    private String name;
    private Integer series_id;
    private Short capacity;
    private Short occupied;
}
