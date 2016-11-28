package com.autocoding.ac.web.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by JFW on 2016/11/11.
 */
public class FileUtilTest {

    public static void main(String[] args) throws IOException {

        String urlString = "http://shp.qpic.cn/bizmp/mUITcKuO6pt8zJV753kXTggbljezTDvjyUIpcOLJx8g4a9ssxtxb7g/";
        String filename = "G:/my.jpg";

        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        OutputStream os = new FileOutputStream(filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }
}
