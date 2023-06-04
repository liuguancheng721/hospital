package com.fengzhu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fengzhu.entity.User;
import com.fengzhu.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
public class WrapperSelectOperatorTest {

    @Resource
    private UserMapper userMapper;

    /**
     * ge、gt、le、lt、isNull、isNotNull
     */
    @Test
    public void select1() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .ge("age",23) // 年龄大于等于23
                .isNotNull("email"); // 邮件不为空的
        List<User> users = userMapper.selectList(queryWrapper);
        log.info("users = " + users);
    }

    /**
     * eq、ne
     */
    @Test
    public void select2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 条件是and关系
        queryWrapper
                .eq("name","莫凡") // name=莫凡
                .ne("age",22); // age!=22

        User users = userMapper.selectOne(queryWrapper);
        log.info("users = " + users);
    }

    /**
     * like(%张%)、notLike、likeLeft(%张)、likeRight(张%)
     */
    @Test
    public void select3() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 条件是and关系
        queryWrapper
                    .notLike("name","孤");

        List<User> users = userMapper.selectList(queryWrapper);

        log.info("users = " + users);
    }

    /**
     * orderBy、orderByDesc、orderByAsc
     */
    @Test
    public void select4() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByDesc("age","id"); // 根据年龄降序再根据id降序
        queryWrapper.orderBy(true,false,"id"); // 根据id降序，第一个参数为true，则执行后面的条件，为false则不执行
        List<User> users = userMapper.selectList(queryWrapper);
        log.info("users = " + users);
    }
}
