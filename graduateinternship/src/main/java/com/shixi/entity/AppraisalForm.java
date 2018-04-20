package com.shixi.entity;

import lombok.Data;

/**
 * @Description: 实习鉴定表
 * @author: mxy
 * @date: 2018年4月20日 下午1:38:45
 */
@Data
public class AppraisalForm {
    private Integer id;
    private String content;
    private Integer userid;
    private String gmtCreate;

    private String gmtModify;

    private Boolean dr;
}
