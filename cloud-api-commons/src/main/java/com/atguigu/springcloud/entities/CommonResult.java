package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author guohang
 * @Description Json封装体CommentResult，传给前端，判断编码是否成功，成功才显示
 * @Date 2020/4/18 17:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {  //泛型：如果装的payment 返回payment,装的order 返回order

    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }
}



