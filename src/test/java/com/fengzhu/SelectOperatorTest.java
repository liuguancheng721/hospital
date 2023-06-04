package com.fengzhu;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fengzhu.entity.User;
import com.fengzhu.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class SelectOperatorTest {

    @Resource
    private UserMapper userMapper;

    /**
     * 根据多个id批量查询
     */
    @Test
    public void selectByIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        log.info("users = " + users);
    }

    /**
     * 简单条件查询
     */
    @Test
    public void selectBySimpleConditions() {
        Map<String,Object> map = new HashMap<>();
        // 这两个条件是AND关系
        map.put("name","Jack");
        map.put("age",21);
        List<User> users = userMapper.selectByMap(map);
        log.info("users = " + users);
    }

    /**
     * 分页查询
     *      1.配置分页查询插件
     *      2.编码
     */
    @Test
    public void pageSelect() {
        Page<User> page = new Page<>(1,3);
        Page<User> pageUsers = userMapper.selectPage(page, null);

        log.info("当前页: " + pageUsers.getCurrent());
        log.info("当前页的数据: " + pageUsers.getRecords());
        log.info("总记录数: " + pageUsers.getTotal());
        log.info("是否有下一页: " + pageUsers.hasNext());
        log.info("是否有上一页: " + pageUsers.hasPrevious());
    }

    /**
     * 插入数据，为下面的逻辑删除做准备
     */
    @Test
    public void insertUser() {
        User user = User.builder()
                .name("莫凡")
                .age(23)
                .email("112233@qq.com")
                .build();
        int insert = userMapper.insert(user);
        log.info("insert = " + insert);
    }

    /**
     * 逻辑删除。数据并没有删除，而是将deleted字段改为了1
     */
    @Test
    public void logicDelete() {
        int i = userMapper.deleteById(1665228221643264002L);
        log.info("i = " + i);
    }

    /**
     * 根据多个id批量删除
     */
    @Test
    public void deleteManyByIds() {
        int i = userMapper.deleteBatchIds(Arrays.asList(1, 2, 3));
        log.info("i = " + i);
    }

    /**
     * 简单条件删除
     */
    @Test
    public void deleteManyBySimpleConditions() {
        Map<String,Object> map = new HashMap();
        map.put("name","独孤阁主");
        map.put("age",22);
        int i = userMapper.deleteByMap(map);
        log.info("i = " + i);
    }
}
