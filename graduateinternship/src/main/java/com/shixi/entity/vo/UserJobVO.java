package com.shixi.entity.vo;

import java.io.Serializable;

import com.shixi.entity.Company;
import com.shixi.entity.Job;
import com.shixi.entity.User;

import lombok.Data;

/**
 * @Description: 用户岗位VO
 * @author: hw
 * @date: 2018年3月28日 下午1:41:38
 */
@Data
public class UserJobVO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer userId;
	/**
	 * 申请岗位信息
	 */
	private Job job;

	/**
	 * 申请岗位所在的公司
	 */
	private Integer companyId;

	private Company company;

	/**
	 * 申请者
	 */
	private User user;

	/**
	 * 申请成功
	 */
	private Boolean success;

	private String gmtCreate;

	private String gmtModify;

	private Boolean dr;
}