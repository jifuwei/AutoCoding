package com.jifuwei.ac.util.file;

import java.io.*;

/**
 * 文件工具类
 * Created by JFW on 2016/11/8.
 */
public class FileUtil {

    /**
     * 拷贝文件及文件夹
     *
     * @param src
     * @param des
     */
    private static void copy(String src, String des) {
        File file1 = new File(src);
        File[] fs = file1.listFiles();
        File file2 = new File(des);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        for (File f : fs) {
            if (f.isFile()) {
                fileCopy(f.getPath(), des + "\\" + f.getName()); //调用文件拷贝的方法
            } else if (f.isDirectory()) {
                copy(f.getPath(), des + "\\" + f.getName());
            }
        }

    }

    /**
     * 文件拷贝
     *
     * @param src
     * @param des
     */
    private static void fileCopy(String src, String des) {

        BufferedReader br = null;
        PrintStream ps = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(src)));
            ps = new PrintStream(new FileOutputStream(des));
            String s = null;
            while ((s = br.readLine()) != null) {
                ps.println(s);
                ps.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (ps != null) ps.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建目录
     * @param path
     * @return
     */
    public static String buildFolder(String path) {
        //读取目录路径
        File file = new File(path);
        //推断是否存在
        if (!file.exists() && !file.isDirectory()) {
            try {
                System.out.println("目录不存在！");
                //生成目录
                file.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("目录存在!");
        }

        return path;
    }

    public static void main(String[] args) {
        copy("G:\\ac", "G:\\copy");
    }
}
