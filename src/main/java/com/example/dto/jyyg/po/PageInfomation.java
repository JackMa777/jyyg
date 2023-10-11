package com.example.dto.jyyg.po;

import lombok.*;

import java.util.Date;

/**
 * 页面参数
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageInfomation {

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 手机号码-加密
     */
    private String phoneEncrypt;

    /**
     * sku
     */
    private String sku;

    /**
     * 店铺Id
     */
    private String storeId;

    /**
     * openId
     */
    private String openId;

    /**
     * 商品Id
     */
    private String commodityId;

    /**
     * 商品数量
     */
    private String commodityQuantity;

    /**
     * 下单人名称
     */
    private String userName;

    /**
     * 创建时间
     */
    private Date createTime;

    private String vcode;

    private String ticket;


}
