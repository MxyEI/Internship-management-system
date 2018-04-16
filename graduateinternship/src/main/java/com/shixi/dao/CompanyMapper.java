package com.shixi.dao;

import com.shixi.entity.Company;

/**
 * @Description: 公司Mapper
 * @author: hw
 * @date: 2018年4月1日 下午1:01:10
 */
public interface CompanyMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Company record);

	int insertSelective(Company record);

	Company selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Company record);

	int updateByPrimaryKeyWithBLOBs(Company record);

	int updateByPrimaryKey(Company record);
}