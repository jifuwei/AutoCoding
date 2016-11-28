package com.autocoding.ac.web.meta.data;

import com.autocoding.ac.util.string.CamelCaseUtil;

import java.sql.Types;
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

    /**
     * 数据库字段转为java类型需要导入的包
     * @return
     */
    public Set<String> dbColumn2JavaImport() {
        Set<String> columnImportList = new HashSet<>();
        String importStr = null;
        for (ACDbColumnMetaInfoData column : this.columnMetaInfoList) {
            importStr = sqlType2JavaTyp(column.getDataType());
            if (importStr != null) {
                columnImportList.add(importStr);
            }
        }
        return columnImportList;
    }

    /**
     * 判断表字段是否为主键
     * @param columnName
     * @return
     */
    public boolean isPrimaryField(String columnName) {
        for (ACDbPrimaryKeyMetaInfoData primaryKey : primaryKeyMetaInfoList) {
            if (primaryKey.getColumnName().equals(columnName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * java.sql.Types值转换为java对应的类
     * @param sqlType
     * @return
     */
    private String sqlType2JavaTyp(int sqlType) {
        switch (sqlType) {
            case Types.BIGINT:
                return "import java.math.BigInteger;";
            case Types.BINARY:
                return null;
            case Types.BIT:
                return null;
            case Types.BLOB:
                return null;
            case Types.CHAR:
                return null;
            case Types.CLOB:
                return null;
            case Types.DATE:
                return "import java.sql.Date;";
            case Types.DECIMAL:
                return "import java.math.BigDecimal;";
            case Types.DOUBLE:
                return null;
            case Types.FLOAT:
                return null;
            case Types.INTEGER:
                return null;
            case Types.JAVA_OBJECT:
                return null;
            case Types.LONGVARBINARY:
                return null;
            case Types.LONGVARCHAR:
                return null;
            case Types.NUMERIC:
                return "import java.math.BigDecimal";
            case Types.OTHER:
                return null;
            case Types.REAL:
                return null;
            case Types.SMALLINT:
                return null;
            case Types.TIME:
                return "import java.sql.Time";
            case Types.TIMESTAMP:
                return "import java.sql.Timestamp";
            case Types.TINYINT:
                return null;
            case Types.VARBINARY:
                return null;
            case Types.VARCHAR:
                return null;
            default:
                return null;
        }
    }

    /**
     * 获取生对应生成的java文件名称
     * @return
     */
    public String getJavaFileName() {
        int num = this.tableName.indexOf("_");
        String appName = this.tableName.substring(0, num);
        String moduleName = CamelCaseUtil.toCapitalizeCamelCase(this.tableName.substring(num + 1, this.tableName.length()));
        return appName.toUpperCase() + moduleName;
    }

    /**
     * 获取表的主键字符串
     * @return
     */
    public String getPrimaryKeys() {
        StringBuffer sb = new StringBuffer();
        for (ACDbPrimaryKeyMetaInfoData primaryKey : primaryKeyMetaInfoList) {
            sb.append("\"").append(primaryKey.getColumnName()).append("\", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
}
