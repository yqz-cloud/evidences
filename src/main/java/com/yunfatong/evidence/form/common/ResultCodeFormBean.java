package com.yunfatong.evidence.form.common;


import com.yunfatong.evidence.common.resultEnum.ResultCode;

/**
 * description:#校验扩展类
 * 比如Service需要返回错误信息和其他实体类
 *
 * @author liran
 * @since 2018/11/15 17:00
 */

public final class ResultCodeFormBean {

    private ResultCode resultCode;

    private String errorMsg;

    private Object object;

    public ResultCodeFormBean() {
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    //设置错误code和错误信息
    public void setCodeOrErrorMsgOrObject(ResultCode resultCode, String errorMsg) {
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
    }

    public void setCodeOrErrorMsgOrObject(ResultCode resultCode, Object object) {
        this.resultCode = resultCode;
        this.object = object;
    }

    //设置错误code和错误信息和object
    public void setCodeOrErrorMsgOrObject(ResultCode resultCode, String errorMsg, Object object) {
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
        this.object = object;
    }

    public ResultCodeFormBean setCodeOrErrorMsgAndReturnThis(ResultCode resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public ResultCodeFormBean setCodeOrErrorMsgAndReturnThis(ResultCode resultCode, String errorMsg) {
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
        return this;
    }

    public ResultCodeFormBean setCodeOrErrorMsgAndReturnThis(ResultCode resultCode, Object object) {
        this.resultCode = resultCode;
        this.object = object;
        return this;
    }

    public ResultCodeFormBean setCodeOrErrorMsgAndReturnThis(ResultCode resultCode, String errorMsg, Object object) {
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
        this.object = object;
        return this;
    }


    public ResultCodeFormBean(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultCodeFormBean(ResultCode resultCode, String errorMsg) {
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
    }

    public ResultCodeFormBean(ResultCode resultCode, Object object) {
        this.resultCode = resultCode;
        this.object = object;
    }

    public ResultCodeFormBean(ResultCode resultCode, String errorMsg, Object object) {
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
        this.object = object;
    }

    public static ResultCodeFormBean createBean(ResultCode resultCode) {
        return new ResultCodeFormBean(resultCode);
    }

    public static ResultCodeFormBean createBean(ResultCode resultCode, String errorMsg) {
        return new ResultCodeFormBean(resultCode, errorMsg);
    }

    public static ResultCodeFormBean createBean(ResultCode resultCode, Object object) {
        return new ResultCodeFormBean(resultCode, object);
    }

    public static ResultCodeFormBean createBean(ResultCode resultCode, String errorMsg, Object object) {
        return new ResultCodeFormBean(resultCode, errorMsg, object);
    }

}
