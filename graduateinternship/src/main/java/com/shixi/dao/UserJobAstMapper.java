package com.shixi.dao;

import java.util.List;
import java.util.Map;

import com.shixi.entity.vo.UserJobVO;

/**
 * @Description: 用户岗位关联AstMapper
 * @author: hw
 * @date: 2018年3月28日 下午1:30:01
 */
public interface UserJobAstMapper {

	/**
	 * @Description: 某个公司的所有申请实习的用户信息
	 * @author: hw
	 * @date: 2018年4月1日 下午12:54:21
	 */
	List<UserJobVO> findAscUserJobs(Map<String, Object> map);

	/**
	 * @Description: 某个公司的所有申请实习的用户数量
	 * @author: hw
	 * @date: 2018年4月1日 下午12:54:40
	 */
	Long getTotlaAscUserJobs(Map<String, Object> map);
}
