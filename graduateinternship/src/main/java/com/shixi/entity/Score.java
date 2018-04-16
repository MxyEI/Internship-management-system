package com.shixi.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @Description: 评价
 * @author: hw
 * @date: 2018年3月29日 下午6:40:23
 */
@Data
public class Score implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 公司id
	 */
	private Integer companyId;

	/**
	 * 教师id
	 */
	private Integer pid;

	/**
	 * 教师评分
	 */
	private Double tscore;

	/**
	 * 公司评分
	 */
	private Double cscore;

	/**
	 * 总分
	 */
	private Double sum;

	/**
	 * 公司评语
	 */
	private String cComment;

	/**
	 * 教师评语
	 */
	private String tComment;

	private String gmtCreate;

	private String gmtModify;

	private Boolean dr;
}