package com.fengzhu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;

    private String name;

    private Integer age;

    private String email;

    /**
     * 在执行插入操作时，MyBatis Plus框架会为createTime自动填充预定值
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 在执行插入和更新操作时，MyBatis Plus框架会为createTime自动填充预定值
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 为实体类添加版本号，防止在修改该实体类的数据时，发生“丢失更新”
     */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    /**
     * 逻辑删除字段。
     *      deleted的值： 1表示删除  0表示未删除
     * @TableLogic 标识之后，查询、更新、删除操作都会自动带上deleted=0的条件，但是删除操作是将查出来的数据deleted设置为1
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic // 用于标识逻辑删除字段
    private Integer deleted;
}
