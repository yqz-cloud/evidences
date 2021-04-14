package com.yunfatong.evidence.controller.base;

import com.yunfatong.evidence.common.resultEnum.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 控制层基类.
 */
public class BaseController implements MessageSourceAware {

    protected MessageSource resource;

    /**
     * 系统日志配置.
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 未知异常，提示请求失败.
     */
    public static final String UNKNOWNEXCEPTION = "请求失败";
    /**
     * 成功的Status Code.
     */
    private static final Integer RESCODE_OK = 0;
    /**
     * 失败的Status Code.
     */
    private static final Integer RESCODE_FAIL = 201;

    /**
     * 描述：获取成功结果
     *
     * @param obj
     * @return
     */
    protected Map<String, Object> getSuccessResult(Object obj) {
        return createJson(RESCODE_OK, "操作成功", obj);
    }

    /**
     * 描述：创建 key 是Map，obj为value的值 并返回
     *
     * @param obj
     * @return
     */
    protected Map<String, Object> getSuccessResult(String key, Object obj) {
        Map<String, Object> map = new HashMap<>(2);
        map.put(key, obj);
        return createJson(RESCODE_OK, "操作成功", map);
    }

    /**
     * 获取默认ajax成功信息.
     *
     * @return
     */
    protected Map<String, Object> getSuccessResult() {
        return createJson(RESCODE_OK, "操作成功", Collections.EMPTY_MAP);
    }


    /**
     * 描述：获取失败结果
     *
     * @param obj
     * @return
     */
    protected Map<String, Object> getFailResult(Object obj) {
        return createJson(RESCODE_FAIL, "操作失败", obj);
    }

    /**
     * 描述：获取失败结果
     *
     * @param code
     * @return
     */
    protected Map<String, Object> getFailResult(Integer code, String msg) {
        return createJson(code, getMessage(msg), Collections.EMPTY_MAP);
    }

    /**
     * 描述：返回失败结果 (错误枚举类)
     *
     * @param resultCode
     * @return
     */
    protected Map<String, Object> getFailResult(ResultCode resultCode) {
        return createJson(resultCode.getCode(), getMessage(resultCode.getMsg()), Collections.EMPTY_MAP);
    }

    /**
     * 描述：返回失败结果 (错误枚举类+错误信息)
     *
     * @param resultCode
     * @return
     */
    protected Map<String, Object> getFailResult(ResultCode resultCode, String errorMsg) {
        if (StringUtils.isNotBlank(errorMsg)) {
            return createJson(resultCode.getCode(), getMessage(resultCode.getMsg() + "-" + errorMsg), Collections.EMPTY_MAP);
        }
        return createJson(resultCode.getCode(), getMessage(resultCode.getMsg()), Collections.EMPTY_MAP);
    }

    /**
     * 参数校验返回错误
     *
     * @param resultCode
     * @param result
     * @return
     * @author liran
     */
    protected Map<String, Object> getFailResult(ResultCode resultCode, BindingResult result) {
        String errorMsg;
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                errorMsg = error.getDefaultMessage();
                if (StringUtils.isNotBlank(errorMsg)) {
                    return createJson(resultCode.getCode(), getMessage(resultCode.getMsg() + "-" + errorMsg), Collections.EMPTY_MAP);
                }
            }
        }
        return createJson(resultCode.getCode(), getMessage(resultCode.getMsg()), Collections.EMPTY_MAP);
    }


    /**
     * 描述：获取失败结果
     *
     * @param code
     * @param msg
     * @param obj
     * @return
     */
    protected Map<String, Object> getFailResult(Integer code, String msg, Object obj) {
        return createJson(code, getMessage(msg), obj);
    }

    /**
     * 描述：获取失败结果
     *
     * @return
     */
    protected Map<String, Object> getFailResult() {
        return createJson(RESCODE_FAIL, "系统异常", Collections.EMPTY_MAP);
    }

    /**
     * 描述：获取失败结果,  直接描述异常信息
     * 不建议使用，建议使用异常码的方式
     *
     * @param msg
     * @return
     */
    protected Map<String, Object> failResult(String msg) {
        return createJson(RESCODE_FAIL, msg, Collections.EMPTY_MAP);
    }


    /**
     * 描述：组装json格式返回结果
     *
     * @param resCode
     * @param msg
     * @param obj
     * @return
     */
    private Map<String, Object> createJson(Integer resCode, String msg, Object obj) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("resultCode", resCode);
        jsonMap.put("resultMsg", msg);
        jsonMap.put("data", obj);
        return jsonMap;
    }


    /**
     * 获取请求作用域.
     *
     * @return
     */
    protected HttpServletRequest requestContext() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取application作用域.
     *
     * @return
     */
    protected ServletContext applicationContext() {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
                .getSession(false);
        if (null != session) {
            return session.getServletContext();
        }
        return null;
    }

    /**
     * 根据相对路径获取资源绝对路径.
     *
     * @param path
     * @return
     */
    protected String getRealPath(String path) {
        ServletContext app = applicationContext();
        if (null != app) {
            String root = app.getRealPath(String.valueOf(File.separatorChar));
            return root + path;
        }
        return path;
    }

    protected String message(String key, Object[] args, String defaultMessage) {
        String result = resource.getMessage(key, args, defaultMessage, requestContext().getLocale());
        return StringUtils.trimToEmpty(result);
    }

    protected String message(String key, Object[] args) {
        String result = resource.getMessage(key, args, "", requestContext().getLocale());
        return StringUtils.trimToEmpty(result);
    }

    protected String getMessage(String code) {
        String result = resource.getMessage(code, null, code, requestContext().getLocale());
        return StringUtils.trimToEmpty(result);
    }


    @Override
    public void setMessageSource(MessageSource messageSource) {
        resource = messageSource;
    }


}
