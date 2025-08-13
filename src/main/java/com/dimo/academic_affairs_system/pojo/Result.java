package com.dimo.academic_affairs_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success(){
        return new Result(200 , "success", null);
    }
    public static Result success(Object data){
        return new Result(200, "success", data);
    }
    public static Result error(Integer code, String msg){
        return new Result(code, msg, null);
    }
}
