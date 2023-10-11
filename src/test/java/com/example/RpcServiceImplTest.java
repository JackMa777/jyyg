package com.example;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.File;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= RpcServiceImplTest.class)
public class RpcServiceImplTest {
    Logger logger = LoggerFactory.getLogger(RpcServiceImplTest.class);


    public static void main(String[] args) {
        /*List<Sku> skus = JSON.parseArray(FileUtil.readUtf8String(new File("./src/main/resources/sku.json")), Sku.class);
        System.out.println(JSON.toJSONString(skus));
        skus.forEach(one->{
            System.out.println("<option value=\""+one.getSku()+"\">"+one.getGoodsName()+"</option>");
        });*/

    }
}
