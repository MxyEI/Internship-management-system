package com.shixi.entity.vo;

import java.io.Serializable;

import com.shixi.entity.User;

import lombok.Data;

/**
 * @Description: 评分VO
 * @author: hw
 * @date: 2018年3月29日 下午2:39:47
 */
@Data
public class ScoreVO implements Serializable{
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

	/**
	 * 学生
	 */
	private User user;

	/**
	 * 教师
	 */
	private User puser;

	private String gmtCreate;

	private String gmtModify;

	private Boolean dr;
}
