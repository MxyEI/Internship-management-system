package com.shixi.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @Description: 用户岗位关联
 * @author: hw
 * @date: 2018年3月28日 下午1:40:59
 */
@Data
public class UserJob implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

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