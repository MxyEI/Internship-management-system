package com.shixi.service;

import java.util.List;
import java.util.Map;

import com.shixi.entity.Company;
import com.shixi.entity.vo.CompanyJobVO;
import com.shixi.entity.vo.CompanyVO;

/**
 * @Description: 公司IService
 * @author: hw
 * @date: 2018年3月28日 下午1:50:02
 */
public interface ICompanyService {
	int deleteByPrimaryKey(Integer id);

	int insert(Company record);

	int insertSelective(Company record);

	Company selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Company record);

	int updateByPrimaryKeyWithBLOBs(Company record);

	int updateByPrimaryKey(Company record);

	List<CompanyVO> findCompanyRecruit(Map<String, Object> map);

	Long getTotalCompanyRecruit(Map<String, Object> map);

	CompanyVO findJobDetail(Map<String, Object> map);

	List<CompanyVO> findCompanyRecruitJobs(Map<String, Object> map);

	CompanyVO findCompanyInfoByUserId(Integer userId);

	//审核
	List<CompanyJobVO> getAllJobInfoWithNewAdd(Map<String, Object> map);
	Long getAllJobInfoWithNewAddCounts(Map<String, Object> map);
}
