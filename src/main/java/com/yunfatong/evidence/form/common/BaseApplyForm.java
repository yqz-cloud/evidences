package com.yunfatong.evidence.form.common;

import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * description:#remark
 *
 * @author liran
 * @since 2018/11/16 15:56
 */
@Data
@ToString
public class BaseApplyForm<T> {

    @NotNull(message = "请求参数applyInfo不能为空")
    @Valid
    private T applyInfo;
}
