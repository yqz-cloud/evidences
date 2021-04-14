package com.yunfatong.evidence.common.exception;

import com.yunfatong.evidence.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(CommonException.class)
    public Object handleCommonException(CommonException e) {
        logger.error("自定义CommonException:" + e.getMessage(), e);
        return getFailResult(e.getCode(), e.getMsg());
    }


    @ExceptionHandler(DuplicateKeyException.class)
    public Object handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error("数据库中已存在该记录异常:" + e.getMessage(), e);
        return getFailResult(500, "数据库中已存在该记录");
    }

    @ExceptionHandler(ValidationException.class)
    public Object handleAuthorizationException(ValidationException e) {
        logger.warn("参数校验异常：" + e.getMessage());
        return getFailResult(e.getCode(), e.getMsg());
    }

//	@ExceptionHandler(AuthorizationException.class)
//	public AjaxResult handleAuthorizationException(AuthorizationException e){
//		logger.error(e.getMessage(), e);
//		return AjaxResult.error("没有权限，请联系管理员授权");
//	}

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        logger.error("服务器异常:" + e.getMessage(), e);
        return getFailResult(500, "服务器异常");
    }
}
