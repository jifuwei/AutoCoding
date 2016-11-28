package ${domainName}.${projectName}.${tableInfo.moduleName}.data.vo;

import ACErrorMsg;
import ACServiceException;
import EntityGroup;
import PrimaryKeyGroup;
import ${domainName}.${projectName}.${tableInfo.moduleName}.data.po.${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO;
import org.hibernate.validator.constraints.NotEmpty;

<#list tableInfo.dbColumn2JavaImport() as import>
${import}
</#list>

/**
 * ${tableInfo.remarks}-VO
 * Created by AutoCoding on ${.now?string("yyyy/MM/dd")}.
 */
public class ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO {
<#list tableInfo.columnMetaInfoList as column>
    <#if tableInfo.isPrimaryField(column.columnName) == true>
    @NotEmpty(message = "{${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO.${column.columnName}.NotEmpty}", groups = {PrimaryKeyGroup.class})
    private String ${column.columnName}; //${column.remarks}

    <#elseif column.isNullable == 'NO'>
    @NotEmpty(message = "{${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO.${column.columnName}.NotEmpty}", groups = {EntityGroup.class})
    private String ${column.columnName}; //${column.remarks}

    <#else>
    private String ${column.columnName}; //${column.remarks}

    </#if>
</#list>

<#list tableInfo.columnMetaInfoList as column>
    public void set${column.columnName?cap_first}(String ${column.columnName}) { this.${column.columnName} = ${column.columnName}; }

    public String get${column.columnName?cap_first}() { return ${column.columnName}; }

</#list>

    public ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO toPO() {
        ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO po = new ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO();
        try {
            <#list tableInfo.columnMetaInfoList as column>
            po.set${column.columnName?cap_first}(${dbColumnVo2PoTMM(column.columnName, column.dataType)});
            </#list>
        } catch (Exception e) {
            e.printStackTrace();
            throw new ACServiceException(ACErrorMsg.ERROR_DATA_IS_MALFORM);
        }

        return po;
    }
}
