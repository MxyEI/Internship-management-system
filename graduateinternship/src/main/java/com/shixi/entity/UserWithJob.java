package com.shixi.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 用户岗位关联
 * @author: hw
 * @date: 2018年3月28日 下午1:40:59
 */
@Data
public class UserWithJob implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	/**
	 * userid
	 */
	private Integer userid;

	/**
	 * 岗位id
	 */
	private Integer jobId;

	/**
	 * 申请成功
	 */
	private Boolean success;

	private String gmtCreate;

	private String gmtModify;

	private Boolean dr;
}