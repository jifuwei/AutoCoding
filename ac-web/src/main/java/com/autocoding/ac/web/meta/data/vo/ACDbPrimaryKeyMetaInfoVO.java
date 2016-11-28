package com.autocoding.ac.web.meta.data.vo;

/**
 * Created by JFW on 2016/10/11.
 */
public class ACDbPrimaryKeyMetaInfoVO {
    private String tableCat;					//表类别（可为 null）
    private String tableSchem;					//表模式（可为 null）
    private String tableName;					//表名称
    private String columnName;					//列名称
    private String keySeq;						//主键中的序列号（值 1 表示主键中的第一列，值 2 表示主键中的第二列）。
    private String pkName;						//主键的名称（可为 null）

    public String getTableCat() {
        return tableCat;
    }

    public void setTableCat(String tableCat) {
        this.tableCat = tableCat;
    }

    public String getTableSchem() {
        return tableSchem;
    }

    public void setTableSchem(String tableSchem) {
        this.tableSchem = tableSchem;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getKeySeq() {
        return keySeq;
    }

    public void setKeySeq(String keySeq) {
        this.keySeq = keySeq;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }
}
