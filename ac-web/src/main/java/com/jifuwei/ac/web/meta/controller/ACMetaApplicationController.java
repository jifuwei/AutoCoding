package com.jifuwei.ac.web.meta.controller;

import com.jifuwei.ac.foundation.data.ACResponseMsg;
import com.jifuwei.ac.foundation.error.ACErrorMsg;
import com.jifuwei.ac.foundation.exception.ACDaoException;
import com.jifuwei.ac.foundation.exception.ACRuntimeException;
import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.foundation.validation.EntityGroup;
import com.jifuwei.ac.web.common.data.FileTypes;
import com.jifuwei.ac.web.meta.data.validation.InitAppMetaInfoFromDbScript;
import com.jifuwei.ac.web.meta.data.vo.ACMetaApplicationVO;
import com.jifuwei.ac.web.meta.service.ACMetaApplicationService;
import org.apache.commons.io.FileUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 应用信息控制类
 * Created by JFW on 2016/11/2.
 */
@RestController
@RequestMapping("/meta/application")
public class ACMetaApplicationController {

    @Resource(name = "ACMetaApplicationServiceImpl")
    private ACMetaApplicationService dataService = null;

    /**
     * 添加数据
     *
     * @param vo
     * @param bindingResult
     * @return
     */
    @RequestMapping("/save")
    public ACResponseMsg save(@Validated({EntityGroup.class}) @RequestBody ACMetaApplicationVO vo, BindingResult bindingResult) {
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
     * 上传sql脚本文件
     * @param multipartFile
     * @param request
     * @return 格式化后的文件名
     */
    @RequestMapping("/upload")
    public String upload(@RequestParam(value = "file-upload") MultipartFile multipartFile, HttpServletRequest request) {
        String fileName = null;
        if (multipartFile == null) {
            return "Not select file";
        }

        if (multipartFile.isEmpty()) {
            return "Not select file";
        } else {
            String originFileName = multipartFile.getOriginalFilename();
            String originFileSuffixName = originFileName.substring(originFileName.lastIndexOf(".") + 1, originFileName.length());
            if (!FileTypes.SCRIPT_TYPE_SQL.equals(originFileSuffixName)) {
                return "The file type does not match";
            }
            fileName = new Date().getTime() + "." + originFileSuffixName;
            try {
                String realPath = request.getSession().getServletContext().getRealPath("/upload/file");//TODO:使用property文件写入
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(realPath, fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileName;
    }

    @RequestMapping("/initAppMetaInfoFromDbScript")
    public ACResponseMsg initAppMetaInfoFromDbScript(@Validated({InitAppMetaInfoFromDbScript.class}) @RequestBody ACMetaApplicationVO vo, BindingResult bindingResult) {
        ACResponseMsg msg = new ACResponseMsg();
        try {
            dataService.initAppMetaInfoFromDbScript(vo);

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