package com.jifuwei.ac.foundation.aop;

import com.jifuwei.ac.foundation.data.ACResponseMsg;
import com.jifuwei.ac.foundation.error.ACErrorMsg;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 用于捕获controller中JSR-303错误，并返回规定的错误消息
 * 使用注意：在controller需要验证的方法入参添加@vaild注解和BindingResult,然后自动验证
 * 验证失败会以json格式字符换返回，格式为ACErrorInfoEnum全局错误格式
 * Created by JFW on 2016/10/6.
 */
public class BindingResultAOP {

    /**
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        ACResponseMsg msg = null;
        BindingResult bindingResult = null;
        StringBuilder sb = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof BindingResult) {
                bindingResult = (BindingResult) arg;
                break;
            }
        }

        if (bindingResult != null) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            if (allErrors.size() > 0) {
                msg = new ACResponseMsg();
                sb = new StringBuilder();
                for (ObjectError error : allErrors) {
                    FieldError fieldError = (FieldError) error;
                    sb.append(fieldError.getDefaultMessage()).append(";");
                }
                msg.errcode = ACErrorMsg.ERROR_DATA_IS_MALFORM.errcode;
                msg.errmsg = sb.toString();
                return msg;
            }
        }
        return joinPoint.proceed();
    }
}
