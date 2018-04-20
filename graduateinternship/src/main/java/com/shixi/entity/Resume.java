package com.shixi.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 简历信息
 * @author: hw
 * @date: 2018年4月19日 下午1:40:45
 */

@Data
public class Resume implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
    *真实姓名
    */
    private String realname;

    /**
     * 专业信息
     */
    private String major;

    /**
     * 基础技能
     */
    private String skill;

    /**
     * 课程信息
     */
    private String course;

    /**
     * 项目经验
     */
    private String project;

    /**
     * 兴趣爱好
     */
    private String hobbies;

    /**
     * 学生id
     */
    private Integer studentid;

    private String gmtCreate;

    private String gmtModify;

    private Boolean dr;

}
