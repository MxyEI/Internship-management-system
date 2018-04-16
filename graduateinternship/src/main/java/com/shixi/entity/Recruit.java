package com.shixi.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @Description: 招聘信息
 * @author: hw
 * @date: 2018年3月28日 下午1:40:45
 */
@Data
public class Recruit implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 公司Id
	 */
	private Integer companyId;

	/**
	 * 失效
	 */
	private Boolean lapse;

	/**
	 * 宣讲会地点
	 */
	private String address;

	/**
	 * 宣讲会开始时间
	 */
	private String startTime;

	/**
	 * 招聘截至时间
	 */
	private String endTime;

	/**
	 * 联系人
	 */
	private String contractUser;

	/**
	 * 联系电话
	 */
	private Integer contractNumber;

	/**
	 * 联系邮箱
	 */
	private String contractEmail;

	/**
	 * 联系地址
	 */
	private String contractAddress;

	/**
	 * 简历投递地址
	 */
	private String sendResumeUrl;

	/**
	 * 简介
	 */
	private String motoley;

	private String gmtCreate;

	private String gmtModify;

	private Boolean dr;
}