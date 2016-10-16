package com.jifuwei.ac.web.db;

import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by JFW on 2016/10/12.
 */
public class ApiTets {
    /**
     * @param
     *            :请求接口
     * @param httpArg
     *            :参数
     * @return 返回结果
     */
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",  "50a117024dbbd32d0cf5fa93f9d1204f");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
//        String httpUrl = "http://apis.baidu.com/baidu_mobile_security/phone_number_service/phone_information_query";
//        String httpArg = "tel=18852982325&location=false";
//        String jsonResult = request(httpUrl, httpArg);
//        System.out.println(jsonResult);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://www.baidu.com";
        System.out.println(restTemplate.getForEntity(url, String.class));
    }
}
