package com.shixi.entity.vo;

import java.io.Serializable;
import java.util.List;

import com.shixi.entity.Job;
import com.shixi.entity.Recruit;

import lombok.Data;

/**
 * @Description: 公司VO
 * @author: hw
 * @date: 2018年3月27日 下午7:18:35
 */
@Data
public class CompanyVO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 公司名称
	 */
	private String name;

	/**
	 * 领域
	 */
	private String world;

	/**
	 * 规模
	 */
	private String scale;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 简介
	 */
	private String about;

	/**
	 * 审核状态
	 */
	private Boolean success;

	/**
	 * 招聘信息
	 */
	private Recruit recruit;

	/**
	 * 公司下发布的岗位
	 */
	private List<Job> jobs;

	/**
	 * 申请该公司的用户岗位关联
	 */
	private List<UserJobVO> userJobVOs;

	private String gmtCreate;

	private String gmtModify;

	private Boolean dr;
}