package com.example;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= RpcSkuServiceImplTest.class)
public class RpcSkuServiceImplTest {
    Logger logger = LoggerFactory.getLogger(RpcSkuServiceImplTest.class);


    public static void main(String[] args) {
        /*List<Sku> skus = JSON.parseArray(FileUtil.readUtf8String(new File("./src/main/resources/sku.json")), Sku.class);
        System.out.println(JSON.toJSONString(skus));
        skus.forEach(one->{
            System.out.println("<option value=\""+one.getSku()+"\">"+one.getGoodsName()+"</option>");
        });*/

    }
}
