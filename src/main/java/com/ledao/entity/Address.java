package com.ledao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 收货地址实体类
 *
 * @author LeDao
 * @company
 * @create 2022-12-25 14:36
 */
@Data
@TableName(value = "t_address")
public class Address {

    /**
     * 编号
     */
    @TableId
    private Integer id;

    /**
     *客户id
     */
    @TableField(value = "customerId")
    private Integer customerId;

    /**
     *收货人名称
     */
    private String name;

    /**
     *收货手机号
     */
    @TableField(value = "phoneNum")
    private String phoneNum;

    /**
     *是否为默认地址
     */
    @TableField(value = "isSelected")
    private Boolean isSelected;

    /**
     *省市区
     */
    private String area;

    /**
     *省市区code,用于省市区选择组件
     */
    @TableField(value = "areaCode")
    private String areaCode;

    /**
     *详细地址
     */
    private String details;

    /**
     *地址说明
     */
    private String description;
}
