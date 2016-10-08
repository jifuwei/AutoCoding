package com.jifuwei.ac.foundation.exception;

import com.jifuwei.ac.foundation.error.ACErrorMsg;

/**
 * dao异常处理
 * Created by JFW on 2016/10/6.
 */
public class ACRuntimeException extends RuntimeException {
    private ACErrorMsg acErrorMsg;

    public ACRuntimeException(ACErrorMsg errorInfoEnum) {
        super(errorInfoEnum.errmsg);
        this.acErrorMsg = errorInfoEnum;
    }

    public ACRuntimeException(Throwable cause) {
        super(cause);
    }

    public ACRuntimeException(ACErrorMsg acErrorMsg, Throwable cause) {
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
