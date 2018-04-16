package com.shixi.service;

import com.shixi.entity.Recruit;

/**
 * @Description: 招聘信息IService
 * @author: hw   
 * @date: 2018年3月28日 下午1:50:26 
 */
public interface IRecruitService {
	int deleteByPrimaryKey(Integer id);

	int insert(Recruit record);

	int insertSelective(Recruit record);

	Recruit selectByPrimaryKey(Integer id);
	
	Recruit selectByCompanyId(Integer id);

	int updateByPrimaryKeySelective(Recruit record);

	int updateByPrimaryKeyWithBLOBs(Recruit record);

	int updateByPrimaryKey(Recruit record);
}
