package com.shixi.entity;

import lombok.Data;

/**
 * @Description: 实习评价表
 * @author: mxy
 * @date: 2018年4月20日 下午1:38:45
 */
@Data
public class Assess {
    private Integer id;
    private String assesscontent;
    private Integer userid;
    private String gmtCreate;

    private String gmtModify;

    private Boolean dr;
}
