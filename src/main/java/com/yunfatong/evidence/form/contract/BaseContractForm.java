package com.yunfatong.evidence.form.contract;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author liran
 * description: 公共 请求校验form
 */
@Data
@ToString
public class BaseContractForm {

    /**
     * 是	String	大客户code。
     */
    @NotBlank(message = "大客户code不可为空")
    private String userMasterCode;

    /**
     * 是	String	小客户code。
     */
    @NotBlank(message = "小客户code不可为空")
    private String userSlaveCode;

    /**
     * 是	String	业务id。 目前可为空
     */
    //@NotNull(message = "业务id不可为空")
    private Long businessId;

    /**
     * 是	String	内容md5
     */
    @NotBlank(message = "内容md5不可为空")
    private String contentMd5;

    /**
     * 是	String	内容sha1
     */
    @NotBlank(message = "内容sha1不可为空")
    private String contentSha1;

    /**
     * 是	String	内容sha256
     */
    @NotBlank(message = "内容sha256不可为空")
    private String contentSha256;

    /**
     * 是	String	hash
     */
    @NotBlank(message = "内容hash不可为空")
    private String contentHash;

    /**
     * 是	Long	明文大小
     */
    @NotNull(message = "明文大小不能为空")
    @Min(value = 1, message = "明文大小不能小于1")
    private Long size;

    /**
     * 是	String	原记录时间
     */
    @NotNull(message = "原记录时间不能为空")
    private Date recordTime;


    /*******根据用户的code获取到user_master_id和user_slave_id*****/

    /**
     * 是	String	大客户code。
     */
    @NotNull(message = "大客户id未配置，请联系管理员配置！")
    private Long userMasterId;

    /**
     * 是	String	小客户code。
     */
    @NotNull(message = "小客户id未配置，请联系管理员配置！")
    private Long userSlaveId;

}
