package ${domainName}.${projectName}.${tableInfo.moduleName}.service;

import ${domainName}.${projectName}.${tableInfo.moduleName}.data.vo.${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO;

/**
 * ${tableInfo.remarks}-逻辑层
 * Created by AutoCoding on ${.now?string("yyyy/MM/dd")}.
 */
public interface ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}Service {
    void save(${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo);

    void delete(${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo);

    void update(${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo);

    ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO find(${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo);
}
