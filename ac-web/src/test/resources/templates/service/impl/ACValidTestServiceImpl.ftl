package ${domainName}.${projectName}.${tableInfo.moduleName}.service.impl;

import com.jifuwei.ac.foundation.error.ACErrorMsg;
import com.jifuwei.ac.foundation.exception.ACServiceException;
import ${domainName}.${projectName}.${tableInfo.moduleName}.dao.${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}Dao;
import ${domainName}.${projectName}.${tableInfo.moduleName}.data.po.${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO;
import ${domainName}.${projectName}.${tableInfo.moduleName}.data.vo.${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO;
import ${domainName}.${projectName}.${tableInfo.moduleName}.service.${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 测试test接口实现类
 * Created by JFW on 2016/10/7.
 */
@Service("${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}ServiceImpl")
public class ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}ServiceImpl implements ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}Service {

    @Resource(name = "${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}DaoImpl")
    private ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}Dao dataDao = null;

    @Override
    public void save(${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo) {
        ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO po = dataDao.getSingle(vo.getPrimaryKeys());
        if (po != null) {
            throw new ACServiceException(ACErrorMsg.ERROR_DUMPLICATE_PRIMARY_KEY);
        }
        po = vo.toPO();
        dataDao.save(po);
    }

    @Override
    public void delete(${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo) {
        dataDao.delete(vo.getPrimaryKeys());
    }

    @Override
    public void update(${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo) {
        ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO po = dataDao.getSingle(vo.getPrimaryKeys());
        if (po == null) {
            throw new ACServiceException(ACErrorMsg.ERROR_RECORD_NOT_EXISTS);
        }
        po = vo.toPO();
        dataDao.update(po);
    }

    @Override
    public ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO find(${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo) {
        ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO po = dataDao.getSingle(vo.getPrimaryKeys());
        if (po == null) {
            throw new ACServiceException(ACErrorMsg.ERROR_RECORD_NOT_EXISTS);
        }
        return po.toVO();
    }
}
