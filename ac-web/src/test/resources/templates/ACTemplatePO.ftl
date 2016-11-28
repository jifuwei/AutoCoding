package ${domainName}.${projectName}.${tableInfo.moduleName}.data.po;

import ACErrorMsg;
import ACServiceException;
import ${domainName}.${projectName}.${tableInfo.moduleName}.data.vo.${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO;

<#list tableInfo.dbColumn2JavaImport() as import>
${import}
</#list>

/**
 * ${tableInfo.remarks}-PO
 * Created by AutoCoding on ${.now?string("yyyy/MM/dd")}.
 */
public class ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO {
<#list tableInfo.columnMetaInfoList as column>
    private ${dbColumnDataTypeTMM(column.dataType)} ${column.columnName}; //${column.remarks}

</#list>
<#list tableInfo.columnMetaInfoList as column>
    public void set${column.columnName?cap_first}(${dbColumnDataTypeTMM(column.dataType)} ${column.columnName}) { this.${column.columnName} = ${column.columnName}; }

    public ${dbColumnDataTypeTMM(column.dataType)} get${column.columnName?cap_first}() { return ${column.columnName}; }

</#list>
    public ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO toVO() {
        ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo = new ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO();
        try {
            <#list tableInfo.columnMetaInfoList as column>
            vo.set${column.columnName?cap_first}(${dbColumnPo2VoTMM(column.columnName, column.dataType)});
            </#list>
        } catch (Exception e) {
            e.printStackTrace();
            throw new ACServiceException(ACErrorMsg.ERROR_DATA_IS_MALFORM);
        }

        return vo;
    }
}
