package com.autocoding.ac.foundation.exception;

import com.autocoding.ac.foundation.error.ACErrorMsg;

/**
 * dao异常处理
 * Created by JFW on 2016/10/6.
 */
public class ACServiceException extends RuntimeException {
    private ACErrorMsg acErrorMsg;

    public ACServiceException(ACErrorMsg errorInfoEnum) {
        super(errorInfoEnum.errmsg);
        this.acErrorMsg = errorInfoEnum;
    }

    public ACServiceException(Throwable cause) {
        super(cause);
    }

    public ACServiceException(ACErrorMsg acErrorMsg, Throwable cause) {
        super(acErrorMsg.errmsg, cause);
        this.acErrorMsg = acErrorMsg;
    }

    public ACErrorMsg getAcErrorMsg() {
        return acErrorMsg;
    }

    public void setAcErrorMsg(ACErrorMsg acErrorMsg) {
        this.acErrorMsg = acErrorMsg;
    }
}
