package ${domainName}.${projectName}.${tableInfo.moduleName}.data.vo;

import com.jifuwei.ac.foundation.error.ACErrorMsg;
import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.foundation.validation.EntityGroup;
import com.jifuwei.ac.foundation.validation.PrimaryKeyGroup;
import ${domainName}.${projectName}.${tableInfo.moduleName}.data.po.${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO;

<#list tableInfo.getSET() as test>
${test}
</#list>

/**
 * 测试VO
 * Created by JFW on 2016/10/6.
 */
public class ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO {
<#list tableInfo.columnMetaInfoList as column>
    private String ${dbColumn2JavaBeanTMM(column.columnName, "FIELD")}; //${column.remarks}
</#list>

<#list tableInfo.columnMetaInfoList as column>
    public void set${dbColumn2JavaBeanTMM(column.columnName, "GET_SET")}(String ${dbColumn2JavaBeanTMM(column.columnName, "FIELD")}) { this.${dbColumn2JavaBeanTMM(column.columnName, "FIELD")} = ${dbColumn2JavaBeanTMM(column.columnName, "FIELD")}; }

    public String get${dbColumn2JavaBeanTMM(column.columnName, "GET_SET")}() { return ${dbColumn2JavaBeanTMM(column.columnName, "FIELD")}; }

</#list>

    public ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO toPO() {
        ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO po = new ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO();
        try {
            po.setAcId(StringUtils.isBlank(this.acId) ? null : Integer.parseInt(this.acId)); //注意：主键如果是自增长时需要考虑为空情况
            po.setMysqlVarchar(this.mysqlVarchar);
            po.setMysqlChar(this.mysqlChar);
            po.setMysqlBlob(this.mysqlBlob.getBytes("UTF8"));
            po.setMysqlText(this.mysqlText);
            po.setMysqlInt(Integer.valueOf(this.mysqlInt));
            po.setMysqlTinyint(Integer.valueOf(this.mysqlTinyint));
            po.setMysqlSmallint(Integer.valueOf(this.mysqlSmallint));
            po.setMysqlMediumint(Integer.valueOf(this.mysqlMediumint));
            po.setMysqlBigint(new BigInteger(this.mysqlBigint));
            po.setMysqlBit(Boolean.parseBoolean(this.mysqlBit));
            po.setMysqlFloat(Float.valueOf(this.mysqlFloat));
            po.setMysqlDouble(Double.valueOf(this.mysqlDouble));
            po.setMysqlDecimal(new BigDecimal(this.mysqlDecimal));
            po.setMysqlBoolean(Boolean.parseBoolean(this.mysqlBoolean));
            po.setMysqlDate(Date.valueOf(this.mysqlDate));
            po.setMysqlTime(Time.valueOf(this.mysqlTime));
            po.setMysqlDatetime(Timestamp.valueOf(this.mysqlDatetime));
            po.setMysqlYear(this.mysqlYear);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ACServiceException(ACErrorMsg.ERROR_DATA_IS_MALFORM);
        }

        return po;
    }
}
