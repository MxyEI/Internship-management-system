package com.shixi.dao;

import com.shixi.entity.vo.CompanyJobVO;
import com.shixi.entity.vo.UserJobVO;

import java.util.List;
import java.util.Map;

/**
 * @Description: 用户岗位关联AstMapper
 * @author: hw
 * @date: 2018年3月28日 下午1:30:01
 */
public interface UserWithJobAstMapper {

	/**
	 * @Description: 某个公司的所有申请实习的用户信息
	 * @author: hw
	 * @date: 2018年4月1日 下午12:54:21
	 */
	List<UserJobVO> findAscUserJobs(Map<String, Object> map);

	/**
	 * @Description: 某个公司的所有申请实习成功的用户信息
	 * @author: hw
	 * @date: 2018年4月1日 下午12:54:21
	 */
	List<UserJobVO> findAscUserJobsWithSuccess(Map<String, Object> map);

	/**
	 * @Description: 某个公司的所有申请实习的用户数量
	 * @author: hw
	 * @date: 2018年4月1日 下午12:54:40
	 */
	Long getTotlaAscUserJobs(Map<String, Object> map);

	/**
	 * @Description: 某个公司的所有申请实习成功的用户数量
	 * @author: hw
	 * @date: 2018年4月1日 下午12:54:40
	 */
	Long getTotlaAscUserJobsWithSuccess(Map<String, Object> map);

	/**
	 * @Description: 某个用户的所有申请信息
	 * @author: hw
	 * @date: 2018年4月21日 下午12:54:40
	 */
	List<UserJobVO> findAscUserJobsByUserid(Map<String, Object> map);


	List<UserJobVO> findAscUserJobsWithUserid(Map<String,Object> map);
	/**
	 * @Description: 某个用户的所有申请信息数量
	 * @author: hw
	 * @date: 2018年4月21日 下午12:54:40
	 */
	Long getTotlaAscUserJobsWithuser(Map<String,Object> map);
}
