package com.example.po;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class Sku implements Serializable {

    private String sgoodsName;

    private String goodsName;

    private String goodsCode;

    private Integer maxQty;

    private String sku ;

    public static List<Sku> skus =JSON.parseArray(FileUtil.readUtf8String(new File("./src/main/resources/sku.json")), Sku.class);
    static {
        Sku.skus.forEach(one->{
            one.setSku();
        });
    }

    public String getSgoodsName() {
        return sgoodsName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setSgoodsName(String sgoodsName) {
        this.sgoodsName = sgoodsName;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public Integer getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(Integer maxQty) {
        this.maxQty = maxQty;
    }

    public String getSku() {
        this.sku = getGoodsCode()+"-"+getMaxQty();
        return getGoodsCode()+"-"+getMaxQty();
    }

    public void setSku() {
        this.sku = getGoodsCode()+"-"+getMaxQty();
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(Sku.skus));
    }
}