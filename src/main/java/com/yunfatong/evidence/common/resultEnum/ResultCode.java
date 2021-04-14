package com.yunfatong.evidence.common.resultEnum;

/**
 * 返回错误码
 */
public enum ResultCode {


    //合同存证相关返回错误码
    EVIDENCE_CONTRACT_RELATED_ADD_CONTRACT_USER_INFO(20000,"新增用户存证接口-请求参数有误"),
    EVIDENCE_CONTRACT_RELATED_ADD_CONTRACT_FILE_INFO(20001,"新增合同文件存证接口-请求参数有误"),
    EVIDENCE_CONTRACT_RELATED_ADD_CONTRACT_INFO(20002,"新新增合同存证接口-请求参数有误"),


    //公共错误码
    SUCCESS(0, "操作成功"),
    OTHER_ERROR(11000, "其它异常"),
    SYSTEM_ERROR(11001, "系统异常"),
    PERSISTENCE_ERROR(11002, "数据库异常"),
    ARGUMENTS_ERROR(11003, "参数错误"),
    SESSION_INVALIDATION_ERROR(11004, "您的登录已失效，请重新登录"),
    JSON_ERROR(11005, "JSON解析错误"),
    ILLEGAL_USER_ERROR(11006, "非法请求,用户不存在"),
    CUSTOMER_NOT_HAVE_ROLE_ERROR(11009, "当前用户无权限执行该操作！");


    private int code;
    private String msg;

     ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

