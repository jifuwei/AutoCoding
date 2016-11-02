package com.jifuwei.ac.web.config.controller;

import com.jifuwei.ac.foundation.data.ACResponseMsg;
import com.jifuwei.ac.foundation.error.ACErrorMsg;
import com.jifuwei.ac.foundation.exception.ACDaoException;
import com.jifuwei.ac.foundation.exception.ACRuntimeException;
import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.foundation.validation.EntityGroup;
import com.jifuwei.ac.web.config.data.vo.ACConfigTemplatePackageVO;
import com.jifuwei.ac.web.config.service.ACConfigTemplatePackageService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 模板套餐信息控制类
 * Created by JFW on 2016/11/2.
 */
@RestController
@RequestMapping("/config/templatePackage")
public class ACConfigTemplatePackageController {

    @Resource(name = "ACConfigTemplatePackageServiceImpl")
    private ACConfigTemplatePackageService dataService = null;

    /**
     * 添加数据
     *
     * @param vo
     * @param bindingResult
     * @return
     */
    @RequestMapping("/save")
    public ACResponseMsg save(@Validated({EntityGroup.class}) @RequestBody ACConfigTemplatePackageVO vo, BindingResult bindingResult) {
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
}
