package com.shixi.dao;

import com.shixi.entity.Company;

import java.util.List;
import java.util.Map;

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

	/**
	 * @Description: 新注册的实习单位待审核的信息
	 * @author: mxy
	 * @date: 2018年5月18日 下午12:54:40
	 */
	List<Company> getAllComInfoWithNewAdd(Map<String, Object> map);

	/**
	 * @Description: 新注册的实习单位待审核的数量
	 * @author: mxy
	 * @date: 2018年5月18日 下午12:54:40
	 */
	Long getAllComInfoWithNewAddCounts(Map<String, Object> map);

	int updateByPrimaryKeySelective(Company record);

	int updateByPrimaryKeyWithBLOBs(Company record);

	int updateByPrimaryKey(Company record);
}