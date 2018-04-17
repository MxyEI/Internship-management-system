package com.shixi.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @Description: 用户
 * @author: hw
 * @date: 2018年3月28日 下午1:40:53
 */
@Data
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 真实姓名
	 */
	private String realname;

	/**
	 * 用户类型,admin,company
	 */
	private String usertype;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 出生日期
	 */
	private String birthday;

	/**
	 * 手机
	 */
	private Integer phoneNumber;

	/**
	 * 邮箱
	 */
	private String email;

	private String gmtCreate;

	private String gmtModify;

	private Boolean dr;
}