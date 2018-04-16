package com.shixi.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @Description: 培训计划
 * @author: hw
 * @date: 2018年3月29日 上午11:41:26
 */
@Data
public class Train implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 公司id
	 */
	private Integer companyId;

	/**
	 * 培训主题
	 */
	private String title;

	/**
	 * 讲师
	 */
	private String teacher;

	/**
	 * 地点
	 */
	private String address;

	/**
	 * 人数
	 */
	private Integer number;

	/**
	 * 开始时间
	 */
	private String startTime;

	/**
	 * 截至时间
	 */
	private String endTime;

	/**
	 * 培训内容
	 */
	private String content;

	private String gmtCreate;

	private String gmtModify;

	private Boolean dr;
}