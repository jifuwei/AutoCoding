package com.autocoding.ac.foundation.data;

/**
 * 数据库源信息
 * Created by JFW on 2016/10/5.
 */
public class DBMetaInfoData {
    //数据库基础信息
    private String userName; //数据库已知用户
    private String url; //数据库URL
    private String databaseProductName; //数据库产品名称
    private String databaseProductVersion; //数据库产品版本
    private String driverName; //数据库驱动名称
    private String driverVersion; //数据库驱动版本

    //表信息
//    private String tableName; //表名称
    private String tableType; //表类型

    //    表信息
    private String tableCat; //表目录
    private String tableName; //表名称
    private String columnName; // 列名称
    private String dataType; //对应的java.sql.Types类型
    private String dataTypeName; //对应的java.sql.Types类型
    private String columnSize; //列大小
    private String decimalDigits; //小数位数
    private String nullAble; //是否为空
    private String remark; //列描述
    private String columnDef; //列默认值
    private String sqlDateType; //sql数据类型
    private String isNullAble; //是否允许空值
    private String isAutoIncrement; //是否递增

    private String primaryKey; //主键


}
