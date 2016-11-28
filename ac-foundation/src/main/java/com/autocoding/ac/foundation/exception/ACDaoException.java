package com.autocoding.ac.foundation.exception;

import com.autocoding.ac.foundation.error.ACErrorMsg;

/**
 * dao异常处理
 * Created by JFW on 2016/10/6.
 */
public class ACDaoException extends RuntimeException {
    private ACErrorMsg acErrorMsg;

    public ACDaoException(ACErrorMsg errorInfoEnum) {
        super(errorInfoEnum.errmsg);
        this.acErrorMsg = errorInfoEnum;
    }

    public ACDaoException(Throwable cause) {
        super(cause);
    }

    public ACDaoException(ACErrorMsg acErrorMsg, Throwable cause) {
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
