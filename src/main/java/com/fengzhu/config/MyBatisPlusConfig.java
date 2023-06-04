package com.fengzhu.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfig {

    /**
     * 创建乐观锁对象，并交给Spring容器管理
     *      乐观锁场景：当要更新一条记录时，希望这条记录没有被别人更新，也就是实现线程安全的数据更新
     *      乐观锁：是一种并发控制策略，通过比较版本字段的值来判断是否有其他事务对该实体进行修改
     * MyBatis Plus是3.4.3版本及之后，使用的乐观锁对象是com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     * @return 乐观锁对象
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 分页查询插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
