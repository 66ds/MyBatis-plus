package com.example.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    @TableField(fill= FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date updateCreate;
    @Version//乐观锁注解
    private Integer version;
    @TableLogic //逻辑删除
    private Integer deleted;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
