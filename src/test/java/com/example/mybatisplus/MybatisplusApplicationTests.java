package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void select() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    /**
     * 插入
     */
    @Test
    void insert() {
        User user = new User();
        user.setName("java7");
        user.setAge(20);
        user.setEmail("1532488760@qq.com");
        int number = userMapper.insert(user);
        System.out.println(user);
    }

    /**
     * 修改
     */
    @Test
    void update(){
        User user = new User();
        user.setId(7);
        user.setName("haha");
        int numer = userMapper.updateById(user);
        System.out.println(numer);
    }

    /**
     * 乐观锁失败测试
     */
    @Test
    void versionTest(){
        //当前用户1
        User user = userMapper.selectById(7);
        user.setName("haha1");
        user.setAge(17);

        //当前用户二插入导致用户1更新失败
        User user1 = userMapper.selectById(7);
        user1.setName("haha2");
        user1.setAge(18);
        userMapper.updateById(user1);

        userMapper.updateById(user);
    }

    /**
     * 多表查询
     */
    @Test
    void listTest(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    /**
     * 分页查询
     */
    @Test
    void limitList(){
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
     }

    /**
     * 逻辑删除(删除后查不到)
     */
    @Test
    void deleteTest(){
        userMapper.deleteById(7);
    }

    /**
     * 条件查询器
     */
    @Test
    void deleteTest1(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","java1");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }




}
