package com.shixi.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description: 页面
 * @author: hw
 * @date: 2018年3月28日 下午1:38:54
 */
@Data
@AllArgsConstructor
public class PageBean implements Serializable{
	private static final long serialVersionUID = 1L;
	// 第几页
	private int page;
	// 每页大小
	private int pageSize;

	public int getStart() {
		return (page - 1) * pageSize;
	}

//	public int getPageSize(){
//		return 10;
//	}
//
//	public PageBean(int page,int pageSize){
//		this.page=page;
//		this.pageSize=pageSize;
//	}
}
