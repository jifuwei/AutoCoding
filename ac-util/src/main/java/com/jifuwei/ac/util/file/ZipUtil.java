package com.jifuwei.ac.util.file;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.File;

/**
 * 用于压缩、解压文件工具类(使用ant工具类，避免中文乱码)
 * Created by JFW on 2016/10/15.
 */
public class ZipUtil {

    /**
     * 压缩文件
     * @param srcFileName 要压缩的文件名
     * @param zipFileName 压缩后的文件名
     */
    public static void zip(String srcFileName, String zipFileName) {
        File srcFile = new File(srcFileName);
        if (srcFile.exists()) {
            Project prj = new Project();

            Zip zip = new Zip();
            zip.setProject(prj);
            zip.setDestFile(new File(zipFileName));

            FileSet fileSet = new FileSet();
            fileSet.setProject(prj);
            fileSet.setDir(srcFile);
            zip.addFileset(fileSet);
            zip.execute();
        }
    }

    /**
     * 解压文件
     * @param inputFileName 要解压的文件名
     * @param unZipFileName 解压目录
     */
    public static void unzip(String inputFileName, String unZipFileName) {
        File srcFile = new File(inputFileName);
        if (srcFile.exists()) {
            Project prj = new Project();
            Expand expand = new Expand();
            expand.setProject(prj);
            expand.setSrc(srcFile);
            expand.setDest(new File(unZipFileName));
            expand.execute();
        }
    }
}
