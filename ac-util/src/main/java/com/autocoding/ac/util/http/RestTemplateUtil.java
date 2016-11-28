package com.autocoding.ac.util.http;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JFW on 2016/10/12.
 */
public class RestTemplateUtil {

    public static ResponseEntity get(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity result = restTemplate.getForEntity(url, String.class);
        return result;
    }

    public static ResponseEntity get(String url, Map<String, Object> urlParams) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity result = restTemplate.getForEntity(url, String.class);
        return result;
    }

    public static ResponseEntity post(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity result = restTemplate.postForEntity(url, null, String.class);
        return result;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        //System.out.println(get("http://10.19.105.129:7001/lhc-wxs/common/getAccessToken.do"));

        CloseableHttpClient httpClient = HttpClientUtils.acceptsUntrustedCertsHttpClient();
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        /*String result = restTemplate.getForObject("https://www.baidu.com",String.class);
        System.out.println(result);*/

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(null, headers);

        Map<String, Object> urlParams = new HashMap<>();
        urlParams.put("code", "kdjgjrkjwjwkrkwerhhipowerweirhweiorweirjeworjeqwroewprjwerpowejo");
        ResponseEntity<String> result = restTemplate.postForEntity("http://10.19.105.129:7001/lhc-wxs/common/getUserIdByCode.do", entity, String.class, urlParams);
        System.out.println(result);
    }
}
