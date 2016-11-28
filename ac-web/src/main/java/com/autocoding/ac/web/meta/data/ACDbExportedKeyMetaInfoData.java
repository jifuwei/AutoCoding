package com.autocoding.ac.web.meta.data;

/**
 * Created by JFW on 2016/10/11.
 */
public class ACDbExportedKeyMetaInfoData {
    private String pktableCat;					//被导入的主键表类别（可为 null）
    private String pktableSchem;				//被导入的主键表模式（可为 null）
    private String pktableName;					//被导入的主键表名称
    private String pkcolumnName;				//被导入的主键列名称
    private String fktableCat;					//外键表类别（可为 null）
    private String fktableSchem;				//外键表模式（可为 null）
    private String fktableName;					//外键表名称
    private String fkcolumnName;				//外键列名称
    private short keySeq;						//外键中的序列号（值 1 表示外键中的第一列，值 2 表示外键中的第二列）
    private short updateRule;					//更新主键时外键发生的变化
    private short deleteRule;					//删除主键时外键发生的变化
    private String pkName;						//主键的名称（可为 null）
    private String fkName;						//外键的名称（可为 null）
    private short deferrability;				//是否可以将对外键约束的评估延迟到提交时间

    public String getPktableCat() {
        return pktableCat;
    }

    public void setPktableCat(String pktableCat) {
        this.pktableCat = pktableCat;
    }

    public String getPktableSchem() {
        return pktableSchem;
    }

    public void setPktableSchem(String pktableSchem) {
        this.pktableSchem = pktableSchem;
    }

    public String getPktableName() {
        return pktableName;
    }

    public void setPktableName(String pktableName) {
        this.pktableName = pktableName;
    }

    public String getPkcolumnName() {
        return pkcolumnName;
    }

    public void setPkcolumnName(String pkcolumnName) {
        this.pkcolumnName = pkcolumnName;
    }

    public String getFktableCat() {
        return fktableCat;
    }

    public void setFktableCat(String fktableCat) {
        this.fktableCat = fktableCat;
    }

    public String getFktableSchem() {
        return fktableSchem;
    }

    public void setFktableSchem(String fktableSchem) {
        this.fktableSchem = fktableSchem;
    }

    public String getFktableName() {
        return fktableName;
    }

    public void setFktableName(String fktableName) {
        this.fktableName = fktableName;
    }

    public String getFkcolumnName() {
        return fkcolumnName;
    }

    public void setFkcolumnName(String fkcolumnName) {
        this.fkcolumnName = fkcolumnName;
    }

    public short getKeySeq() {
        return keySeq;
    }

    public void setKeySeq(short keySeq) {
        this.keySeq = keySeq;
    }

    public short getUpdateRule() {
        return updateRule;
    }

    public void setUpdateRule(short updateRule) {
        this.updateRule = updateRule;
    }

    public short getDeleteRule() {
        return deleteRule;
    }

    public void setDeleteRule(short deleteRule) {
        this.deleteRule = deleteRule;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public String getFkName() {
        return fkName;
    }

    public void setFkName(String fkName) {
        this.fkName = fkName;
    }

    public short getDeferrability() {
        return deferrability;
    }

    public void setDeferrability(short deferrability) {
        this.deferrability = deferrability;
    }
}
