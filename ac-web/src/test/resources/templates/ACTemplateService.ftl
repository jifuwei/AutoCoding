package ${domainName}.${projectName}.${tableInfo.moduleName}.service;

import ${domainName}.${projectName}.${tableInfo.moduleName}.data.vo.${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO;

/**
 * 测试test接口
 * Created by JFW on 2016/10/7.
 */
public interface ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}Service {
    void save(${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo);

    void delete(${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo);

    void update(${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo);

    ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO find(${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo);
}
