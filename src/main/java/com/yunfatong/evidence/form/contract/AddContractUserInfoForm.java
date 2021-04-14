package com.yunfatong.evidence.form.contract;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author liran
 * description: 合同用户信息存证 请求校验form
 */
@Data
@ToString
public class AddContractUserInfoForm extends BaseContractForm {

    /**
     * 是	String	用户uuid。
     */
    @NotBlank(message = "用户uuid不可为空")
    private String userUuid;

    /**
     * 是	int	存证类型：0企业，1个人。
     */
    @NotNull(message = "存证类型不可为空")
    @Range(min = 0, max = 1, message = "存证类型不正确（0企业 1个人）")
    private Integer evidenceType;

}
