package com.shixi.dao;

import com.shixi.entity.Recruit;

/**
 * @Description: 招聘信息Mapper
 * @author: hw
 * @date: 2018年4月1日 下午12:59:46
 */
public interface RecruitMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Recruit record);

	int insertSelective(Recruit record);

	Recruit selectByPrimaryKey(Integer id);

	Recruit selectByCompanyId(Integer id);

	int updateByPrimaryKeySelective(Recruit record);

	int updateByPrimaryKeyWithBLOBs(Recruit record);

	int updateByPrimaryKey(Recruit record);
}