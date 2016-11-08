package ${domainName}.${projectName}.${tableInfo.moduleName}.controller;

import com.jifuwei.ac.foundation.data.ACResponseMsg;
import com.jifuwei.ac.foundation.error.ACErrorMsg;
import com.jifuwei.ac.foundation.exception.ACDaoException;
import com.jifuwei.ac.foundation.exception.ACRuntimeException;
import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.foundation.validation.EntityGroup;
import com.jifuwei.ac.foundation.validation.PrimaryKeyGroup;
import ${domainName}.${projectName}.${tableInfo.moduleName}.data.vo.${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO;
import ${domainName}.${projectName}.${tableInfo.moduleName}.service.${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ${tableInfo.remarks}-控制层
 * Created by AutoCoding on ${.now?string("yyyy/MM/dd")}.
 */
@RestController
@RequestMapping("/${tableInfo.moduleName}/${tableInfo.businessTablename}")
public class ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}Controller {

    @Resource(name = "${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}Impl")
    private ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}Service dataService = null;

    /**
     * 添加数据
     * @param vo
     * @param bindingResult
     * @return
     */
    @RequestMapping("/save")
    public ACResponseMsg save(@Validated({EntityGroup.class}) @RequestBody ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo, BindingResult bindingResult) {
        ACResponseMsg msg = new ACResponseMsg();
        try {
            dataService.save(vo);

            msg.errcode = ACErrorMsg.CALL_SUCCESS.errcode;
            msg.errmsg = ACErrorMsg.CALL_SUCCESS.errmsg;
        } catch (ACServiceException e) {
            msg = e.getAcErrorMsg().toResponseMsg();
        } catch (ACDaoException e) {
            msg = e.getAcErrorMsg().toResponseMsg();
        } catch (ACRuntimeException e) {
            msg = e.getAcErrorMsg().toResponseMsg();
        }
        return msg;
    }

    /**
     * 删除数据
     * @param vo
     * @param bindingResult
     * @return
     */
    @RequestMapping("/delete")
    public ACResponseMsg delete(@Validated({PrimaryKeyGroup.class}) @RequestBody ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo, BindingResult bindingResult) {
        ACResponseMsg msg = new ACResponseMsg();
        try {
            dataService.delete(vo);

            msg.errcode = ACErrorMsg.CALL_SUCCESS.errcode;
            msg.errmsg = ACErrorMsg.CALL_SUCCESS.errmsg;
        } catch (ACServiceException e) {
            msg = e.getAcErrorMsg().toResponseMsg();
        } catch (ACDaoException e) {
            msg = e.getAcErrorMsg().toResponseMsg();
        } catch (ACRuntimeException e) {
            msg = e.getAcErrorMsg().toResponseMsg();
        }
        return msg;
    }

    /**
     * 更新数据
     * @param vo
     * @param bindingResult
     * @return
     */
    @RequestMapping("/update")
    public ACResponseMsg update(@Validated({PrimaryKeyGroup.class, EntityGroup.class}) @RequestBody ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo, BindingResult bindingResult) {
        ACResponseMsg msg = new ACResponseMsg();
        try {
            dataService.update(vo);

            msg.errcode = ACErrorMsg.CALL_SUCCESS.errcode;
            msg.errmsg = ACErrorMsg.CALL_SUCCESS.errmsg;
        } catch (ACServiceException e) {
            msg = e.getAcErrorMsg().toResponseMsg();
        } catch (ACDaoException e) {
            msg = e.getAcErrorMsg().toResponseMsg();
        } catch (ACRuntimeException e) {
            msg = e.getAcErrorMsg().toResponseMsg();
        }
        return msg;
    }

    /**
     * 查询数据
     * @param vo
     * @param bindingResult
     * @return
     */
    @RequestMapping("/find")
    public ACResponseMsg find(@Validated({PrimaryKeyGroup.class}) @RequestBody ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO vo, BindingResult bindingResult) {
        ACResponseMsg msg = new ACResponseMsg();
        try {
            vo = dataService.find(vo);

            msg.errcode = ACErrorMsg.CALL_SUCCESS.errcode;
            msg.errmsg = ACErrorMsg.CALL_SUCCESS.errmsg;
            msg.respData = vo;
        } catch (ACServiceException e) {
            msg = e.getAcErrorMsg().toResponseMsg();
        } catch (ACDaoException e) {
            msg = e.getAcErrorMsg().toResponseMsg();
        } catch (ACRuntimeException e) {
            msg = e.getAcErrorMsg().toResponseMsg();
        }
        return msg;
    }
}
