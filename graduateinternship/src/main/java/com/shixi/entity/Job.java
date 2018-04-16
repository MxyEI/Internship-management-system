package com.shixi.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @Description: 岗位
 * @author: hw
 * @date: 2018年3月28日 下午1:38:09
 */
@Data
public class Job implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 公司id
	 */
	private Integer companyId;

	/**
	 * 岗位名称
	 */
	private String name;

	/**
	 * 专业要求
	 */
	private String major;

	/**
	 * 招聘人数
	 */
	private Integer num;

	/**
	 * 工作地点
	 */
	private String address;

	/**
	 * 学历要求
	 */
	private String degree;

	/**
	 * 工资
	 */
	private String money;

	/**
	 * 职位描述
	 */
	private String jobDescribe;

	/**
	 * 失效
	 */
	private Boolean lapse;

	private String gmtCreate;

	private String gmtModify;

	private Boolean dr;
}