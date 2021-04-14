package com.yunfatong.evidence.form.contract;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author liran
 * description: 合同文件信息存证 请求校验form
 */
@Data
@ToString
public class AddContractFileInfoForm extends BaseContractForm {

    /**
     * 是	String	合同uuid。
     */
    @NotBlank(message = "合同uuid不可为空")
    private String contractUuid;

    /**
     * 是	int	存证类型：0所有，1pdf，2图片
     */
    @NotNull(message = "存证类型不可为空")
    @Range(min = 0, max = 2, message = "存证类型不正确(0所有，1pdf，2图片)")
    private Integer evidenceType;

}
