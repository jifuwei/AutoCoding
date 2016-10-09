package com.jifuwei.ac.web.valid.controller;

import com.jifuwei.ac.foundation.data.ACResponseMsg;
import com.jifuwei.ac.foundation.error.ACErrorMsg;
import com.jifuwei.ac.foundation.exception.ACDaoException;
import com.jifuwei.ac.foundation.exception.ACRuntimeException;
import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.web.valid.data.vo.ACValidTestVO;
import com.jifuwei.ac.web.valid.service.ACValidTestService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 测试controller
 * Created by JFW on 2016/10/6.
 */
@RestController
@RequestMapping("/valid/test")
public class ACValidTestController {

    @Resource(name = "ACValidTestServiceImpl")
    private ACValidTestService dataService = null;

    @RequestMapping("/add")
    public ACResponseMsg add(@Valid @RequestBody ACValidTestVO vo, BindingResult bindingResult) {
        ACResponseMsg msg = new ACResponseMsg();
        try {
            dataService.add(vo);

            msg.errcode = ACErrorMsg.CALL_SUCCESS.errcode;
            msg.errmsg = ACErrorMsg.CALL_SUCCESS.errmsg;
        } catch (ACServiceException e) {
            ACErrorMsg acErrorMsg = e.getAcErrorMsg();
            msg.errcode = acErrorMsg.errcode;
            msg.errmsg = acErrorMsg.errmsg;
        } catch (ACDaoException e) {
            ACErrorMsg acErrorMsg = e.getAcErrorMsg();
            msg.errcode = acErrorMsg.errcode;
            msg.errmsg = acErrorMsg.errmsg;
        } catch (ACRuntimeException e) {
            ACErrorMsg acErrorMsg = e.getAcErrorMsg();
            msg.errcode = acErrorMsg.errcode;
            msg.errmsg = acErrorMsg.errmsg;
        }
        return msg;
    }
}
