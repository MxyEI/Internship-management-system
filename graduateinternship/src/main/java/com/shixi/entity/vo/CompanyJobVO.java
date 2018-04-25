package com.shixi.entity.vo;

import com.shixi.entity.Company;
import com.shixi.entity.Job;
import lombok.Data;

import java.io.Serializable;

@Data
public class CompanyJobVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 申请的岗位信息
     */
    private Job job;

    /**
     * 申请岗位所在的公司
     */

    private Company company;

    /**
     * 审核状态
     */
    private Boolean success;

    private String gmtCreate;

    private String gmtModify;

}
