package com.example.http;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.management.resources.agent;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.GZIPInputStream;

public class HTTPClient {
    public volatile static ConcurrentHashMap<String,HttpURLConnection> skillMap = new ConcurrentHashMap<String,HttpURLConnection>();

    public volatile static ConcurrentHashMap<String,HttpURLConnection> captchaMap = new ConcurrentHashMap<String,HttpURLConnection>();

    private static  String orderUrl = "https://jyyg.wm.tobaccohb.com.cn/marketing-gold-leaf/api/goldLeafGo/goGetCanbuy/saveOrder.json";
    public volatile static String agent="";
    public volatile static String lx="";
    public static boolean getConn(String strUrl, String ticket, String userAgent, String phone){
        boolean flag = true;
        HttpURLConnection urlconn = null;
        try {
            HttpURLConnection connection = skillMap.get(phone);
            HttpURLConnection httpURLConnection = captchaMap.get(phone);
            if (connection != null) {
                httpURLConnection.disconnect();
                connection.disconnect();
            }
            urlconn = getHttpURLConnection(orderUrl, RequestMethod.POST, ticket, userAgent);
            httpURLConnection = getHttpURLConnection(strUrl, RequestMethod.GET, ticket, userAgent);
            skillMap.put(phone,urlconn);
            captchaMap.put(phone,httpURLConnection);
        }catch (Exception e){
            flag = false;
        }
        return flag;
    }

    @NotNull
    private static HttpURLConnection getHttpURLConnection(String strUrl, RequestMethod requestMethod, String ticket, String userAgent) throws IOException {
        HttpURLConnection urlconn;
        URL url = new URL(strUrl);
        urlconn = (HttpURLConnection) url.openConnection();
        urlconn.setDoInput(true);// 设置输入流采用字节流模式
        urlconn.setDoOutput(true);
        urlconn.setRequestMethod(requestMethod.name());
        // post请求不能使用缓存
        urlconn.setUseCaches(false);
        urlconn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        urlconn.setRequestProperty("User-Agent", StringUtils.hasText(userAgent)? userAgent :agent);
        urlconn.setRequestProperty("Accept-Encoding", "gzip,compress,br,deflate");
        urlconn.setRequestProperty("lx-ticket", StringUtils.hasText(ticket)? ticket :lx);
        urlconn.setRequestProperty("Connection", "keep-alive");
        urlconn.setRequestProperty("Referer", "https://servicewechat.com/wxc8b875c7375961bb/9/page-frame.html");
        urlconn.setConnectTimeout(0);//5000
        urlconn.setReadTimeout(0);//8000
        urlconn.connect();// 链接服务器并发送消息
        return urlconn;
    }

    public static String sendMsg(String phone ,String jsonStr,String type){
        OutputStreamWriter writer = null;
        BufferedReader bufferedReader = null;
        StringBuilder result = new StringBuilder();
        HttpURLConnection connection = null;
        if (type.equals("skillMap")){
            connection = skillMap.get(phone);
        }
        if (type.equals("captchaMap")){
            connection = captchaMap.get(phone);
        }
        if (connection == null){
            return "";
        }
        try {
            writer = new OutputStreamWriter(connection.getOutputStream());
            System.out.println("2=" + jsonStr);
            writer.write(jsonStr);
            writer.flush();// 发送，清除缓存

            // 开始接收返回的数据
            /*bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String readLine = "";
            while ((readLine = bufferedReader.readLine()) != null) {
                result.append(readLine);
            }*/
            if (type.equals("captchaMap")){
                InputStreamReader reader = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                int len = 0;
                while((len = reader.read()) != -1){
                    result.append((char)len);
                }
            }
            if (type.equals("skillMap")){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                GZIPInputStream unzip = new GZIPInputStream(connection.getInputStream());
                byte[] buffer = new byte[256];
                int n;
                while ((n = unzip.read(buffer)) >= 0) {
                    out.write(buffer, 0, n);
                }
                result.append(out.toString(StandardCharsets.UTF_8.toString()));
            }
            System.out.println(result);
        }catch (MalformedURLException e) {
            e.printStackTrace();
            result.delete(0, result.length());
        } catch (IOException e) {
            e.printStackTrace();
            result.delete(0, result.length());
        } catch (Exception e) {
            e.printStackTrace();
            result.delete(0, result.length());
        }
        finally {
            //不关下次接着用
            try {
                if (writer != null) {
                    writer.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (IOException e) {
                result.delete(0, result.length());
            }
        }
        return result.toString();
    }


    public static String sendHttpRequest(String strUrl, JSONObject jsonData) {
        System.out.println("jsonData =" + jsonData.toString());

        try {
            System.out.println("======" + new String(jsonData.toString().getBytes("utf-8"), "utf-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        URL url = null;
        StringBuilder result = new StringBuilder();
        BufferedReader bufferedReader = null;
        HttpURLConnection urlconn = null;
        OutputStreamWriter writer = null;
        // String string = "username:password";
        // String encoding = Base64.encode(string.getBytes());
        try {
            url = new URL(strUrl);
            urlconn = (HttpURLConnection) url.openConnection();
            urlconn.setDoInput(true);// 设置输入流采用字节流模式
            urlconn.setDoOutput(true);
            urlconn.setRequestMethod("GET");
            // post请求不能使用缓存
            urlconn.setUseCaches(false);
            urlconn.setRequestProperty("Content-Type", "application/json");
            urlconn.setRequestProperty("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 16_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/8.0.37(0x1800252d) NetType/WIFI Language/zh_CN");
            urlconn.setRequestProperty("Accept-Encoding","gzip,compress,br,deflate");
            urlconn.setRequestProperty("lx-ticket","eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAADWRTYvUMQzGv8v_vC5Jmrd6UwRRcL24MHhL20TXw86ogwjid7dz8JBAnoYnzS9_jvMln9-t4-VxjtPHr_Ihx-k7vYFfbz-fx8Pp_bq-fvj0eNwdT5fdI37Pfo_OO7b2-DN_vHj1JZ-v-01HsHlODOokSyFUG6gMCliDKH3sTLfMBSOmU-0CC6c2nqXQ7aZTrA46DbKgWaTJsKG8-waLs0JqcxpMVAYks_Mimwo2kQ2AkCcstdZQFkkzJVdLhGILX3PVduuhq5en9ZqTRFGX1P4geC3JNX27AamJ2RLqTc2sqq8cFW2YTh8do3Q7cptbyC5ITOJ7D-cANuyQgemobg1i9g4Y0SQmlNHyiTKnY2fMMuHujQeQBbJmEIf1nu59DHOxxWtt3t-uTxs0CCJha-BZm5bTRl5rr1tgCKa3Y8W-yJ6sNxxCd0f-vvwXXIX-_gPEpnWl-AEAAA.IzMTFtht82OWgHWJkX_eJh7XDrJH8N472yuwQ7YrIis");
            urlconn.setRequestProperty("Connection","keep-alive");
            urlconn.setRequestProperty("Referer","https://servicewechat.com/wxc8b875c7375961bb/9/page-frame.html");
            //  + encoding
            // urlconn.setRequestProperty("Authorization", "Basic "+ encoding);
            urlconn.setConnectTimeout(0);//5000
            urlconn.setReadTimeout(0);//8000
            urlconn.connect();// 链接服务器并发送消息
            for (int i = 0; i < 99; i++) {
                Thread.sleep(1000);
                System.out.println("第"+i+"秒");
            }
            writer = new OutputStreamWriter(urlconn.getOutputStream());
            System.out.println("2=" + jsonData.toString());
            writer.write(jsonData.toString());
            writer.flush();// 发送，清除缓存

            // 开始接收返回的数据
            /*bufferedReader = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), "UTF-8"));
            String readLine = "";
            while ((readLine = bufferedReader.readLine()) != null) {
                result.append(readLine);
            }*/
            InputStreamReader reader = new InputStreamReader(urlconn.getInputStream(), "UTF-8");
            int len = 0;
            while((len = reader.read()) != -1){
                result.append((char)len);
            }
            System.out.println(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            result.delete(0, result.length());
        } catch (IOException e) {
            e.printStackTrace();
            result.delete(0, result.length());
        } catch (Exception e) {
            e.printStackTrace();
            result.delete(0, result.length());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (urlconn != null) {
                    urlconn.disconnect();
                }
            } catch (IOException e) {
                result.delete(0, result.length());
            }
        }
        return result.toString();
    }
}
