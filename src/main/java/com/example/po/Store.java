package com.example.po;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Store implements Serializable {
    private String storeName;
    private String storeCode;
    public static List<Store> list = JSON.parseArray("[{\"storeCode\":\"103140\",\"storeName\":\"泛悦城店\"},{\"storeCode\":\"103136\",\"storeName\":\"珞喻路店\"},{\"storeCode\":\"103004\",\"storeName\":\"群光广场专卖店\"},{\"storeCode\":\"103016\",\"storeName\":\"理工大专卖店\"},{\"storeCode\":\"103003\",\"storeName\":\"武商亚贸专卖店\"},{\"storeCode\":\"2020\",\"storeName\":\"金叶惠民付家坡店\"},{\"storeCode\":\"103037\",\"storeName\":\"中南二路奥山店\"},{\"storeCode\":\"103018\",\"storeName\":\"宏城金都专卖店\"},{\"storeCode\":\"103130\",\"storeName\":\"汉街专卖店\"},{\"storeCode\":\"103069\",\"storeName\":\"金禾时尚店\"},{\"storeCode\":\"103073\",\"storeName\":\"金地广场店\"},{\"storeCode\":\"103011\",\"storeName\":\"武胜路专卖店\"},{\"storeCode\":\"103015\",\"storeName\":\"四唯路专卖店\"},{\"storeCode\":\"103132\",\"storeName\":\"金叶阳光武商众圆店\"},{\"storeCode\":\"103010\",\"storeName\":\"荣西专卖店\"},{\"storeCode\":\"103002\",\"storeName\":\"武商世贸专卖店\"},{\"storeCode\":\"2046\",\"storeName\":\"金叶惠民青山店\"},{\"storeCode\":\"103138\",\"storeName\":\"K11航空路店\"},{\"storeCode\":\"2052\",\"storeName\":\"金叶惠民台北路店\"},{\"storeCode\":\"103007\",\"storeName\":\"iamlab丝享馆\"},{\"storeCode\":\"103014\",\"storeName\":\"金色华府专卖店\"},{\"storeCode\":\"103008\",\"storeName\":\"万豪国际专卖店\"},{\"storeCode\":\"103006\",\"storeName\":\"七里庙专卖店\"},{\"storeCode\":\"103075\",\"storeName\":\"泛海国际店\"},{\"storeCode\":\"103012\",\"storeName\":\"长港路专卖店\"},{\"storeCode\":\"103135\",\"storeName\":\"金叶阳光武商沌口店\"},{\"storeCode\":\"2094\",\"storeName\":\"金叶惠民四明路店\"},{\"storeCode\":\"2037\",\"storeName\":\"金叶惠民黄陂店\"}]", Store.class);

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public static void main(String[] args) {
        AtomicReference<String> s = new AtomicReference<>("");
        list.forEach(one -> {
            s.set("<option value=\"" + one.getStoreCode() + "\">" + one.getStoreName() + "</option>\n" + s);
        });
        System.out.println(s);
    }
}
