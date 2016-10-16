package com.jifuwei.ac.web.meta.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by JFW on 2016/10/11.
 */
public class ACDbTableMetaInfoData {
    private String tableCat;					//表类别（可为 null）
    private String tableSchem;					//表模式（可为 null）
    private String tableName;					//表名称
    private String tableType;					//表类型。典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。
    private String remarks;						//表的解释性注释
    private String typeCat;						//类型的类别（可为 null）
    private String typeSchem;					//类型模式（可为 null）
    private String typeName;					//类型名称（可为 null）
    private String selfReferencingColName;		//有类型表的指定 "identifier" 列的名称
    private String refGeneration;				//指定在 SELF_REFERENCING_COL_NAME 中创建值的方式,这些值为 "SYSTEM"、"USER" 和 "DERIVED"。（可能为 null）

    private List<ACDbColumnMetaInfoData> columnMetaInfoList = null; //列信息
    private List<ACDbPrimaryKeyMetaInfoData> primaryKeyMetaInfoList = null; //主键信息
    private List<ACDbExportedKeyMetaInfoData> exportedKeyMetaInfoList = null; //外键信息

    private String moduleName; //模块名
    private String businessTablename; //业务表名
    private String moduleAndBusinessTableName; //模块加业务表名

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getBusinessTablename() {
        return businessTablename;
    }

    public void setBusinessTablename(String businessTablename) {
        this.businessTablename = businessTablename;
    }

    public String getModuleAndBusinessTableName() {
        return moduleAndBusinessTableName;
    }

    public void setModuleAndBusinessTableName(String moduleAndBusinessTableName) {
        this.moduleAndBusinessTableName = moduleAndBusinessTableName;
    }

    public List<ACDbColumnMetaInfoData> getColumnMetaInfoList() {
        return columnMetaInfoList;
    }

    public void setColumnMetaInfoList(List<ACDbColumnMetaInfoData> columnMetaInfoList) {
        this.columnMetaInfoList = columnMetaInfoList;
    }

    public List<ACDbPrimaryKeyMetaInfoData> getPrimaryKeyMetaInfoList() {
        return primaryKeyMetaInfoList;
    }

    public void setPrimaryKeyMetaInfoList(List<ACDbPrimaryKeyMetaInfoData> primaryKeyMetaInfoList) {
        this.primaryKeyMetaInfoList = primaryKeyMetaInfoList;
    }

    public List<ACDbExportedKeyMetaInfoData> getExportedKeyMetaInfoList() {
        return exportedKeyMetaInfoList;
    }

    public void setExportedKeyMetaInfoList(List<ACDbExportedKeyMetaInfoData> exportedKeyMetaInfoList) {
        this.exportedKeyMetaInfoList = exportedKeyMetaInfoList;
    }

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

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTypeCat() {
        return typeCat;
    }

    public void setTypeCat(String typeCat) {
        this.typeCat = typeCat;
    }

    public String getTypeSchem() {
        return typeSchem;
    }

    public void setTypeSchem(String typeSchem) {
        this.typeSchem = typeSchem;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSelfReferencingColName() {
        return selfReferencingColName;
    }

    public void setSelfReferencingColName(String selfReferencingColName) {
        this.selfReferencingColName = selfReferencingColName;
    }

    public String getRefGeneration() {
        return refGeneration;
    }

    public void setRefGeneration(String refGeneration) {
        this.refGeneration = refGeneration;
    }

    public Set<String> getSET() {
        Set<String> test = new HashSet<>();
        test.add("1111");
        test.add("222");
        test.add("1133311");
        test.add("1144411");
        test.add("1111");
        return test;
    }
}
