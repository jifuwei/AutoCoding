package com.autocoding.ac.foundation.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取properties文件工具类，基于spring
 * Created by JFW on 2016/11/14.
 */
public class PropertiesUtil {

    /**
     * 读取指定的配置文件信息
     *
     * @param resourceName
     * @return
     */
    public static Properties getProperties(String resourceName) throws IOException {
        Resource resource = new ClassPathResource(resourceName);
        Properties prop = null;
        prop = PropertiesLoaderUtils.loadProperties(resource);
        return prop;
    }

    public static void main(String[] args) throws IOException {
        Properties props = PropertiesUtil.getProperties("log4j.properties");
        System.out.println(props.getProperty("log4j.category.org.springframework"));
    }
}
