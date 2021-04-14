package com.yunfatong.evidence.common.exception;

import com.yunfatong.evidence.common.resultEnum.ResultCode;

/**
 * 参数校验异常
 */
public class ValidationException extends CommonException {
    private static final long serialVersionUID = 1L;

    public ValidationException() {
        super("param validation fail", null);
    }

    public ValidationException(int code, String errorMsg) {
        super(errorMsg, code);
    }

    /**
     * 错误的resultCode
     *
     * @param code
     */
    public ValidationException(ResultCode code) {
        super(code.getMsg(), code.getCode());
    }

    /**
     * 错误的resultCode
     *
     * @param code
     */
    public ValidationException(ResultCode code, String errorMsg) {
        super(code.getMsg() + "-" + errorMsg, code.getCode());
    }
}
