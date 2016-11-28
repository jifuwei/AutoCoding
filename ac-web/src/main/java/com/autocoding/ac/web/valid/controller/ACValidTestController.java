package com.autocoding.ac.web.valid.controller;

import com.autocoding.ac.foundation.data.ACResponseMsg;
import com.autocoding.ac.foundation.error.ACErrorMsg;
import com.autocoding.ac.foundation.exception.ACDaoException;
import com.autocoding.ac.foundation.exception.ACRuntimeException;
import com.autocoding.ac.foundation.exception.ACServiceException;
import com.autocoding.ac.foundation.validation.PrimaryKeyGroup;
import com.autocoding.ac.foundation.validation.EntityGroup;
import com.autocoding.ac.web.valid.service.ACValidTestService;
import com.autocoding.ac.web.valid.data.vo.ACValidTestVO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试controller
 * Created by JFW on 2016/10/6.
 */
@RestController
@RequestMapping("/valid/test")
public class ACValidTestController {

    @Resource(name = "ACValidTestServiceImpl")
    private ACValidTestService dataService = null;

    /**
     * 添加数据
     * @param vo
     * @param bindingResult
     * @return
     */
    @RequestMapping("/save")
    public ACResponseMsg save(@Validated({EntityGroup.class}) @RequestBody ACValidTestVO vo, BindingResult bindingResult) {
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
    public ACResponseMsg delete(@Validated({PrimaryKeyGroup.class}) @RequestBody ACValidTestVO vo, BindingResult bindingResult) {
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
    public ACResponseMsg update(@Validated({PrimaryKeyGroup.class, EntityGroup.class}) @RequestBody ACValidTestVO vo, BindingResult bindingResult) {
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
    public ACResponseMsg find(@Validated({PrimaryKeyGroup.class}) @RequestBody ACValidTestVO vo, BindingResult bindingResult) {
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
