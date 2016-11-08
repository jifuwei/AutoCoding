package ${domainName}.${projectName}.${tableInfo.moduleName}.dao;

import com.jifuwei.ac.foundation.dao.IDao;
import ${domainName}.${projectName}.${tableInfo.moduleName}.data.po.${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO;

/**
 * ${tableInfo.remarks}-数据层接口
 * Created by AutoCoding on ${.now?string("yyyy/MM/dd")}.
 */
public interface ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}Dao extends IDao<${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO> {
}
