package ${domainName}.${projectName}.${tableInfo.moduleName}.data;

<#list tableInfo.dbColumn2JavaImport() as import>
${import}
</#list>

/**
 * ${tableInfo.remarks}-Data
 * Created by AutoCoding on ${.now?string("yyyy/MM/dd")}.
 */
public class ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}Data {
<#list tableInfo.columnMetaInfoList as column>
    private ${dbColumnDataTypeTMM(column.dataType)} ${column.columnName}; //${column.remarks}

</#list>
<#list tableInfo.columnMetaInfoList as column>
    public void set${column.columnName?cap_first}(${dbColumnDataTypeTMM(column.dataType)} ${column.columnName}) { this.${column.columnName} = ${column.columnName}; }

    public ${dbColumnDataTypeTMM(column.dataType)} get${column.columnName?cap_first}() { return ${column.columnName}; }

</#list>
}
