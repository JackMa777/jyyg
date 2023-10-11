package com.example.dto.jyyg.ao;

import lombok.*;
import sun.management.Agent;

import java.util.Date;

/**
 * 页面参数
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageInfoAo {

    /**
     * 手机号码aes加密
     */
    private String phoneEncrypt;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 店铺Id
     */
    private String storeNo;

    /**
     * 商品Id_商品数量
     */
    private String product;

    /**
     * 下单人小程序id
     */
    private String openid;

    /**
     * 创建时间
     */
    private Date createTime;

    private String vcode;

    private String ticket;

    private String userAgent;





}
