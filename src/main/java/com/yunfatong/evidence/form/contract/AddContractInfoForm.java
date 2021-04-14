package com.yunfatong.evidence.form.contract;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author liran
 * description: 证表-合同、人和文件关联信息存证 请求校验form
 */
@Data
@ToString
public class AddContractInfoForm extends BaseContractForm {

    /**
     * 是	String	合同uuid。
     */
    @NotBlank(message = "合同uuid不可为空")
    private String contractUuid;

    /**
     * 是	int	存证类型：0电子合同，1影印合同，2混合合同
     */
    @NotNull(message = "存证类型不可为空")
    @Range(min = 0, max = 2, message = "存证类型不正确(0电子合同，1影印合同，2混合合同)")
    private Integer evidenceType;

}
