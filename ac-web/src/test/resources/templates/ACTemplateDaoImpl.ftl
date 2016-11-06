package ${domainName}.${projectName}.${tableInfo.moduleName}.dao.impl;

import com.jifuwei.ac.foundation.dao.IDaoImpl;
import ${domainName}.${projectName}.${tableInfo.moduleName}.dao.${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}Dao;
import ${domainName}.${projectName}.${tableInfo.moduleName}.data.po.${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO;
import org.springframework.stereotype.Repository;

/**
 * 测试dao接口实现类
 * Created by JFW on 2016/10/7.
 */
@Repository("${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}DaoImpl")
public class ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}DaoImpl extends IDaoImpl<${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO> implements ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}Dao {

    public ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}DaoImpl() {
        super();
        this.tableName = "${tableInfo.tableName}";
        this.tableKeys = new String[] {"${tableInfo.tableName}"};
    }

    @Override
    protected String generateWhere(Object[] paramArrayOfObject) {
        return null;
    }
}
