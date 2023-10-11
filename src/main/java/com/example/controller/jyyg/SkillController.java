package com.example.controller.jyyg;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.dto.jyyg.ao.PageInfoAo;
import com.example.http.HTTPClient;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/JYYGSkill")
public class SkillController {
    private static Logger logger = LoggerFactory.getLogger(SkillController.class);
    private static String url = "https://jyyg.wm.tobaccohb.com.cn/marketing-gold-leaf/sms/verification/code/smsSend.json";
    private static  String orderUrl = "https://jyyg.wm.tobaccohb.com.cn/marketing-gold-leaf/api/goldLeafGo/goGetCanbuy/saveOrder.json";
    @GetMapping("getConnect")
    @ResponseBody
    public Boolean getConnect(@RequestBody PageInfoAo ao) throws IOException {
        String urlString=url+"?phone="+ao.getPhone()+"&type=1&lx-cors-request=true";
        boolean b = HTTPClient.getConn(urlString, ao.getTicket(), ao.getUserAgent(),ao.getPhone());
        logger.info("com.example.util.SecKill.getConnect() result: {} jsonStr :{}",b,JSONUtil.toJsonStr(ao));
        return b;
    }
    @PostMapping("saveOrder")
    @ResponseBody
    public JSONObject saveOrder(@RequestBody PageInfoAo ao) throws IOException {
        HashMap<String, Object> map = new HashMap<String, Object>() {{
            put("phone", ao.getPhone());
            put("openid", ao.getOpenid());
            put("storeNo", ao.getStoreNo());
            put("ip", "unknown");
            put("vcode", ao.getVcode());
            put("product", ao.getProduct());
            put("type", 1);
        }};
        String json = JSONUtil.toJsonStr(map);
        JSONObject post = post(orderUrl, json, ao.getTicket(), ao.getUserAgent());
        logger.info("com.example.util.SecKill.saveOrder() result: {} jsonStr :{}",post,json);
        return post;
    }
    @PostMapping("saveOrder2")
    @ResponseBody
    public JSONObject saveOrder2(@RequestBody PageInfoAo ao) throws IOException {
        HashMap<String, Object> map = new HashMap<String, Object>() {{
            put("phone", ao.getPhoneEncrypt());
            put("openid", ao.getOpenid());
            put("storeNo", ao.getStoreNo());
            put("ip", "unknown");
            put("vcode", ao.getVcode());
            put("product", ao.getProduct());
            put("type", 1);
        }};
        String json = JSONUtil.toJsonStr(map);
        String post = HTTPClient.sendMsg(ao.getPhone(), json,"skillMap");
        logger.info("com.example.util.SecKill.saveOrder() result: {} jsonStr :{}",post,json);
        return JSONObject.parseObject(post);
    }

    public static JSONObject post(String url, String data,String ticket,String userAgent) {
        logger.info("req url: {}, req data: {}",url, data);
        JSONObject resultForJson = null;
        //设置媒体类型。application/json表示传递的是一个json格式的对象
        MediaType mediaType = MediaType.parse("application/json");
        //创建okHttpClient对象
        OkHttpClient okhttp = new OkHttpClient();
        //设置okhttp超时
        okhttp.newBuilder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(60000, TimeUnit.MILLISECONDS)
                .build();
        //创建RequestBody对象，将参数按照指定的MediaType封装
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(mediaType, data);
        //创建一个Request
        Request request = new Request.Builder()
                .addHeader("Host", "jyyg.wm.tobaccohb.com.cn")
                .addHeader("Connection", "keep-alive")
                .addHeader("lx-ticket", ticket)
                .addHeader("User-Agent", StringUtils.hasText(userAgent) ? userAgent : "Mozilla/5.0 (iPhone; CPU iPhone OS 16_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/8.0.37(0x1800252d) NetType/WIFI Language/zh_CN")
                .addHeader("Referer", "https://servicewechat.com/wxc8b875c7375961bb/7/page-frame.html")
                .addHeader("content-type", "application/json")
                .addHeader("Accept", "*/*")
                .post(requestBody).url(url).build();

        try (Response response = okhttp.newCall(request).execute()){
            if (!response.isSuccessful())
                throw new IOException("unexpected code.." + response);
            if (response.body() != null) {
                String result = response.body().string();
                logger.info("req url: {}, resp data: {}",url, result);
                resultForJson = JSONObject.parseObject(result);
            }
        } catch (IOException e) {
            logger.error("req url: {}, exception: ", url, e);
        }
        return resultForJson;
    }

    @PostMapping("getCapture")
    @ResponseBody
    public JSONObject getCapture(@RequestBody PageInfoAo ao) {
        HashMap<String, Object> map = new HashMap<String, Object>() {{
            put("phone", ao.getPhone());
            put("type", 1);
            put("lx-cors-request", true);
        }};
        String result = HttpRequest.get(url)
                .header("User-Agent", StringUtils.hasText(ao.getUserAgent())?ao.getUserAgent():"Mozilla/5.0 (iPhone; CPU iPhone OS 16_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/8.0.37(0x1800252d) NetType/WIFI Language/zh_CN")
                .header("Referer", "https://servicewechat.com/wxc8b875c7375961bb/9/page-frame.html")
                .header("content-type", "application/json")
                .header("Accept-Encoding", "gzip,compress,br,deflate")
                .header("lx-ticket", ao.getTicket())
                .header("Connection", "keep-alive")
                .header("Host", "jyyg.wm.tobaccohb.com.cn")
                .form(map).execute().body();
        logger.info("com.example.util.SecKill.getImageStr result: {}",result);
        return JSONObject.parseObject(result);
    }
    @PostMapping("getCapture2")
    @ResponseBody
    public JSONObject getCapture2(@RequestBody PageInfoAo ao) {
        String urlString=url+"?phone="+ao.getPhone()+"&type=1&lx-cors-request=true";
        boolean b = HTTPClient.getConn(urlString, ao.getTicket(), ao.getUserAgent(),ao.getPhone());
        String result = HTTPClient.sendMsg(ao.getPhone(), new JSONObject().toString(),"captchaMap");
        logger.info("com.example.util.SecKill.getCapture2 result: {}",result);
        return JSONObject.parseObject(result);
    }
}
