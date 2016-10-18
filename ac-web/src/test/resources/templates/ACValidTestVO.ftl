package ${domainName}.${projectName}.${tableInfo.moduleName}.data.vo;

import com.jifuwei.ac.foundation.error.ACErrorMsg;
import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.foundation.validation.EntityGroup;
import com.jifuwei.ac.foundation.validation.PrimaryKeyGroup;
import ${domainName}.${projectName}.${tableInfo.moduleName}.data.po.${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO;
import org.hibernate.validator.constraints.NotEmpty;

<#list tableInfo.dbColumn2JavaImport() as import>
${import}
</#list>

/**
 * 测试VO
 * Created by JFW on 2016/10/6.
 */
public class ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO {
<#list tableInfo.columnMetaInfoList as column>
    <#if tableInfo.isPrimaryField(column.columnName) == true>
    @NotEmpty(message = "{ACValidTestVO.${dbColumn2JavaBeanTMM(column.columnName, "FIELD")}.NotEmpty}", groups = {PrimaryKeyGroup.class})
    private String ${dbColumn2JavaBeanTMM(column.columnName, "FIELD")}; //${column.remarks}
    <#elseif column.isNullable == 'NO'>
    @NotEmpty(message = "{ACValidTestVO.${dbColumn2JavaBeanTMM(column.columnName, "FIELD")}.NotEmpty}", groups = {EntityGroup.class})
    private String ${dbColumn2JavaBeanTMM(column.columnName, "FIELD")}; //${column.remarks}
    <#else>
    private String ${dbColumn2JavaBeanTMM(column.columnName, "FIELD")}; //${column.remarks}
    </#if>
</#list>

<#list tableInfo.columnMetaInfoList as column>
    public void set${dbColumn2JavaBeanTMM(column.columnName, "GET_SET")}(String ${dbColumn2JavaBeanTMM(column.columnName, "FIELD")}) { this.${dbColumn2JavaBeanTMM(column.columnName, "FIELD")} = ${dbColumn2JavaBeanTMM(column.columnName, "FIELD")}; }

    public String get${dbColumn2JavaBeanTMM(column.columnName, "GET_SET")}() { return ${dbColumn2JavaBeanTMM(column.columnName, "FIELD")}; }

</#list>

    public ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO toPO() {
        ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO po = new ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO();
        try {
            <#list tableInfo.columnMetaInfoList as column>
            po.set${dbColumn2JavaBeanTMM(column.columnName, "GET_SET")}(${dbColumnVo2PoTMM(dbColumn2JavaBeanTMM(column.columnName, "FIELD"), column.dataType)});
            </#list>
        } catch (Exception e) {
            e.printStackTrace();
            throw new ACServiceException(ACErrorMsg.ERROR_DATA_IS_MALFORM);
        }

        return po;
    }
}
