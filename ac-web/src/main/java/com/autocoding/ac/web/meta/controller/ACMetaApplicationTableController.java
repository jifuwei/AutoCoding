package com.autocoding.ac.web.meta.controller;

import com.autocoding.ac.foundation.data.ACResponseMsg;
import com.autocoding.ac.foundation.error.ACErrorMsg;
import com.autocoding.ac.foundation.exception.ACDaoException;
import com.autocoding.ac.foundation.exception.ACRuntimeException;
import com.autocoding.ac.foundation.exception.ACServiceException;
import com.autocoding.ac.foundation.validation.EntityGroup;
import com.autocoding.ac.web.meta.data.vo.ACMetaApplicationTableVO;
import com.autocoding.ac.web.meta.service.ACMetaApplicationTableService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 应用表信息控制类
 * Created by JFW on 2016/11/2.
 */
@RestController
@RequestMapping("/meta/applicationTable")
public class ACMetaApplicationTableController {

    @Resource(name = "ACMetaApplicationTableServiceImpl")
    private ACMetaApplicationTableService dataService = null;

    /**
     * 添加数据
     *
     * @param vo
     * @param bindingResult
     * @return
     */
    @RequestMapping("/save")
    public ACResponseMsg save(@Validated({EntityGroup.class}) @RequestBody ACMetaApplicationTableVO vo, BindingResult bindingResult) {
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
