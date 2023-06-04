package com.fengzhu;

import com.fengzhu.entity.User;
import com.fengzhu.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
class HospitalApplicationTests {

    /**
     * 采用@Autowired爆红是因为找不到注入的对象，因为类是动态创建的，但是程序还是可以正确运行的
     */
//    @Autowired
    @Resource
    private UserMapper userMapper;

    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        log.info("users = " + users);
    }

    /**
     * 执行插入操作后，user并没有设置id，但是插入的数据却有id属性值
     *      1665187663247851522，这个值是MyBatis Plus自动生成的
     *          MyBatis Plus默认的主键策略是ASSIGN_ID(使用了雪花算法）
     *              也可以在application.yml配置文件中配置全局的MyBatis Plus的主键策略
     *                  mybatis-plus.global-config.db-config.id-type=auto 设置全局主键策略为auto
     *              还可以在实体类字段中配置局部MyBatis Plus的主键策略
     *                  @TableId(type = IdType.AUTO)
     *                  private long id;
     */
    @Test
    public void insertOne() {
        User user = User.builder()
                .name("孙悟空")
                .age(226)
                .email("sunwukong@qq.com")
                .build();
        int insert = userMapper.insert(user);
        log.info("insert = " + insert);
    }

    /**
     * 自动填充createTime和updateTime
     */
    @Test
    public void insertAutoCreateTime() {
        User user = User.builder()
                .name("李四")
                .age(23)
                .email("22134042@qq.com")
                .build();
        int insert = userMapper.insert(user);
        log.info("insert = " + insert);
    }

    @Test
    public void insertAutoUpdateTime() {
        User user = User.builder()
                .id(1665223187043991553L)
                .name("刘氓")
                .age(21)
                .email("22134042@qq.com")
                .build();
        int insert = userMapper.updateById(user);
        log.info("insert = " + insert);
    }

    /**
     * 乐观锁。这里并不做并发的数据更新，而是查看数据更新后是否版本号有没有发生改变
     * 在代码中，不需要我们编写user.setVersion()，MyBatis Plus已经帮我们写了
     */
    @Test
    public void optimisticVersion() {
        // 根据id查询
        User user = userMapper.selectById(1665228221643264002L);
        // 更新数据
        user.setName("独孤");
        int res = userMapper.updateById(user);
        log.info("res = " + res);
    }
}
