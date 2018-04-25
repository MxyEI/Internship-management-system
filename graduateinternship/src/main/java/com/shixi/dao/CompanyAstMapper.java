package com.shixi.dao;

import java.util.List;
import java.util.Map;

import com.shixi.entity.vo.CompanyJobVO;
import com.shixi.entity.vo.CompanyVO;

/**
 * @Description: 公司多表关联Mapper
 * @author: hw
 * @date: 2018年3月23日 下午3:09:58
 */
public interface CompanyAstMapper {
	/**
	 * @Description: 查询公司和招聘信息
	 * @author: hw
	 * @date: 2018年4月1日 下午1:01:21
	 */
	List<CompanyVO> findCompanyRecruit(Map<String, Object> map);

	/**
	 * @Description: 获取公司和招聘信息数量
	 * @author: hw
	 * @date: 2018年4月1日 下午1:01:29
	 */
	Long getTotalCompanyRecruit(Map<String, Object> map);

	/**
	 * @Description: 获取某个岗位信息、招聘信息和公司信息
	 * @author: hw
	 * @date: 2018年4月1日 下午1:01:36
	 */
	CompanyVO findJobDetail(Map<String, Object> map);

	/**
	 * @Description: 获取某个公司下的招聘信息和生效的岗位信息
	 * @author: hw
	 * @date: 2018年4月1日 下午1:01:43
	 */
	List<CompanyVO> findCompanyRecruitJobs(Map<String, Object> map);

	/**
	 * @Description: 通过用户id获取该用户所在的公司
	 * @author: hw
	 * @date: 2018年4月1日 下午1:01:49
	 */
	CompanyVO findCompanyInfoByUserId(Integer userId);

}
